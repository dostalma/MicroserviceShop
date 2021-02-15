package cz.dostalma.microserviceshop.service;

import cz.dostalma.microserviceshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getAllProducts();

    List<Product> getProductsByIds(List<Long> productIds);

    Optional<Product> getProductByName(String name);

    Optional<Product> getProductById(Long id);

    void deleteProductByName(String name);

    Product saveProduct(Product product);
}
