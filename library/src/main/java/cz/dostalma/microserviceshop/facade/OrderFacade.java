package cz.dostalma.microserviceshop.facade;

import cz.dostalma.microserviceshop.dto.CheckoutOrderDto;
import cz.dostalma.microserviceshop.dto.OrderDto;

import java.util.List;

public interface OrderFacade {

    public void createOrder(CheckoutOrderDto checkoutOrderDto);

    public List<OrderDto> getAllOrdersByCustomerEmail(String email);

}
