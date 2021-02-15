package cz.dostalma.microserviceshop.controller;

import cz.dostalma.microserviceshop.dto.ProductDto;
import cz.dostalma.microserviceshop.facade.ProductFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class RestProductController {

    private static final Logger logger
            = LoggerFactory.getLogger(RestProductController.class);

    @Autowired
    ProductFacade productFacade;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        logger.info("REST Request to retrieve all products");
        List<ProductDto> list = productFacade.getAllProducts();
        return list;
    }

}
