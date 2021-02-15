package cz.dostalma.microserviceshop.controller;

import cz.dostalma.microserviceshop.dto.ProductDto;
import cz.dostalma.microserviceshop.facade.ProductFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger
            = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductFacade productFacade;

    @GetMapping
    public String getProducts(Model model) {
        logger.info("Request to retrieve all products");
        List<ProductDto> productList = productFacade.getAllProducts();

        model.addAttribute("products", productList);
        return "pages/products";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProductByid(Model model, @PathVariable Long id) {
        logger.info("Request to retrieve a product by id");
        ProductDto product = productFacade.getProductById(id);

        model.addAttribute("product", product);
        return "pages/product-display";

    }
}
