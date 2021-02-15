package cz.dostalma.microserviceshop.repository;

import cz.dostalma.microserviceshop.model.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findAllByIdIn(List<Long> productIds);

    @Query(value = "SELECT * FROM PRODUCT p " +
            "JOIN PRODUCT_ORDERS po ON p.PRODUCT_ID = po.PRODUCT_ID " +
            "JOIN ORDERS o ON o.ORDERS_ID = po.ORDERS_ID " +
            "WHERE o.ORDERS_ID = :orderId",
            nativeQuery = true)
    List<Product> findAllByOrderId(@Param("orderId") Long orderId);

    void deleteByName(String name);
}
