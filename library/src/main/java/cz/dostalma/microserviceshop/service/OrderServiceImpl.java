package cz.dostalma.microserviceshop.service;

import cz.dostalma.microserviceshop.model.Order;
import cz.dostalma.microserviceshop.model.ProductInOrder;
import cz.dostalma.microserviceshop.repository.OrderRepository;
import cz.dostalma.microserviceshop.repository.ProductInOrderRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @Override
    public Order saveOrder(Order order) {
        Order savedOrderEntity = orderRepository.saveAndFlush(order);

        order.getProducts().forEach(productInOrder -> productInOrder.setId(
                new ProductInOrder.ProductsInOrderKey(productInOrder.getProduct().getId(), savedOrderEntity.getId())));
        productInOrderRepository.saveAll(order.getProducts());

        return savedOrderEntity;
    }

    @Override
    public List<Order> getAllOrdersByCustomerId(Long id) {
        List<Order> orders = orderRepository.findAllByCustomerId(id);
        orders.forEach(order -> Hibernate.initialize(order.getProducts()));

        return orders;
    }

    @Override
    public List<Order> getAllOrdersByCustomerEmail(String email) {
        List<Order> orders = orderRepository.findAllOrdersByCustomerEmail(email);
        orders.forEach(order -> Hibernate.initialize(order.getProducts()));

        return orders;
    }
}
