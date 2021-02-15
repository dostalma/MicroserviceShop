package cz.dostalma.microserviceshop.facade;

import cz.dostalma.microserviceshop.dto.ProductDto;
import cz.dostalma.microserviceshop.model.Product;
import cz.dostalma.microserviceshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductFacadeImpl implements ProductFacade {

    private static final Logger logger
            = LoggerFactory.getLogger(ProductFacadeImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    ProductService productService;

    @Override
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByIds(List<Long> productIds) {
        return productService.getProductsByIds(productIds).stream()
                        .map(product -> modelMapper.map(product, ProductDto.class))
                        .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return modelMapper.map(productService.getProductById(id).orElse(null), ProductDto.class);
    }

    @Override
    public void createProduct(ProductDto dto) {
        productService.saveProduct(modelMapper.map(dto, Product.class));
    }

    @Override
    public void updateProduct(ProductDto dto) {
        Product entity = modelMapper.map(dto, Product.class);
        if (dto.getId() != null) {

        }
    }
}
