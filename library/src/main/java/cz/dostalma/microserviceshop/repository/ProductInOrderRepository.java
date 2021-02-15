package cz.dostalma.microserviceshop.repository;

import cz.dostalma.microserviceshop.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, ProductInOrder.ProductsInOrderKey> {

    @Query("SELECT p FROM ProductInOrder p " +
           " JOIN p.order o " +
           " WHERE p.order.customerId IN (" +
           "   SELECT c.id FROM Customer c " +
            "   WHERE c.email = :email)")
    Set<ProductInOrder> getAllProductInOrderOfCustomerByEmail(String email);
}
