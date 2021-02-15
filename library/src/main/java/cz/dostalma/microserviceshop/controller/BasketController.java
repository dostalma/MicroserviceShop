package cz.dostalma.microserviceshop.controller;

import cz.dostalma.microserviceshop.dto.ProductDto;
import cz.dostalma.microserviceshop.facade.ProductFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import java.util.*;

@Controller
@RequestMapping("/basket")
public class BasketController {

    private static final Logger logger
            = LoggerFactory.getLogger(BasketController.class);

    @Autowired
    ProductFacade productFacade;

    @GetMapping
    public String showBasketContent(@CookieValue(value = "basket-content", required = false) String basketContent, Model model) {
        logger.info("Request to show basket content");

        if (StringUtils.isEmpty(basketContent)) {
            model.addAttribute("products", Collections.emptyList());
        } else {
            Set<Long> productIds = new HashSet<>();
            for (String strId : basketContent.split(";")) {
                try {
                    productIds.add(Long.parseLong(strId));
                } catch (NumberFormatException ex) {
                    logger.error("Error during parsing of basket product id: " + strId, ex);
                }
            }

            List<ProductDto> productList = productFacade.getProductsByIds(new ArrayList<>(productIds));

            model.addAttribute("products", productList);
        }
        return "pages/basket";
    }

    @PostMapping("/proceedToCheckout")
    public ModelAndView proceedToCheckout(){
        int i = 0;
        return new ModelAndView("redirect:/checkout");
    }

}
