package cz.dostalma.microserviceshop.controller;

import cz.dostalma.microserviceshop.dto.CustomerDto;
import cz.dostalma.microserviceshop.dto.OrderDto;
import cz.dostalma.microserviceshop.facade.CustomerFacade;
import cz.dostalma.microserviceshop.facade.OrderFacade;
import cz.dostalma.microserviceshop.validation.ExistingCustomerException;
import cz.dostalma.microserviceshop.validation.ValidationFieldErrorUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger
            = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerFacade customerFacade;

    @Autowired
    OrderFacade orderFacade;

    @Autowired
    ValidationFieldErrorUtil validationFieldErrorUtil;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String customerDetails(Model model, /*@PathVariable Long id,*/ Principal principal) {
        logger.info("Request to retrieve customer details");

        if (principal == null || StringUtils.isEmpty(principal.getName())) {
            return "redirect:/";
        }

        CustomerDto customer = customerFacade.getCustomerByEmail(principal.getName());

        model.addAttribute("customer", customer);
        return "pages/customer-details";
    }


    @RequestMapping(value = "/account/orders", method = RequestMethod.GET)
    public String orderHistory(Model model, Principal principal) {
        logger.info("Request to enter the order history page");
        CustomerDto customerDto = new CustomerDto();

        if (principal != null) {
            List<OrderDto> orders = orderFacade.getAllOrdersByCustomerEmail(principal.getName());
            model.addAttribute("orders", orders);
        }

        model.addAttribute("customerDto", customerDto);

        return "pages/order-history";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(Model model) {
        logger.info("Request to enter the registration page");
        CustomerDto customerDto = new CustomerDto();

        model.addAttribute("customerDto", customerDto);

        return "pages/registration";
    }

    @PostMapping("/register")
    public String registerCustomer(@Valid @ModelAttribute CustomerDto customerDto, BindingResult bindingResult, Model model) {
        logger.info("Request to register a new customer");

        model.addAttribute("customerDto", customerDto);

        if (bindingResult.hasErrors()) {
            model.addAttribute("success", "false");
            validationFieldErrorUtil.addFieldErrorsFromObjectErrors(bindingResult);
            return "pages/registration";
        }

        try {
            customerFacade.registerNewCustomer(customerDto);
        } catch (ExistingCustomerException ex) {
            bindingResult.addError(new FieldError("customerDto", "email", ex.getMessage()));
            return "pages/registration";
        }

        model.addAttribute("success", "true");

        return "pages/registration";
    }


    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute CustomerDto customer, Model model) {
        customerFacade.updateCustomer(customer);

        model.addAttribute("customer", customer);

        return "pages/customer-details";
    }
}
