package cz.dostalma.microserviceshop.service;

import cz.dostalma.microserviceshop.model.Order;

import java.util.List;

public interface OrderService {

    public Order saveOrder(Order order);

    List<Order> getAllOrdersByCustomerId(Long id);

    List<Order> getAllOrdersByCustomerEmail(String email);

}
