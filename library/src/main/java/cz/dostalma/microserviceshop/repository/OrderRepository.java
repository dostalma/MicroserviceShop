package cz.dostalma.microserviceshop.repository;

import cz.dostalma.microserviceshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByCustomerId(Long id);

    @Query("SELECT o FROM Order o " +
            " WHERE o.customerId IN (" +
            "   SELECT c.id FROM Customer c " +
            "   WHERE c.email = :email)")
    List<Order> findAllOrdersByCustomerEmail(String email);

}
