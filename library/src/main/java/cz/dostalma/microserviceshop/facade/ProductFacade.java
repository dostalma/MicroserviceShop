package cz.dostalma.microserviceshop.facade;

import cz.dostalma.microserviceshop.dto.ProductDto;

import java.util.List;

public interface ProductFacade {

    List<ProductDto> getAllProducts();

    List<ProductDto> getProductsByIds(List<Long> productIds);

    ProductDto getProductById(Long id);

    void createProduct(ProductDto product);

    void updateProduct(ProductDto product);
}
