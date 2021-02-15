package cz.dostalma.microserviceshop.facade;

import cz.dostalma.microserviceshop.dto.CheckoutOrderDto;
import cz.dostalma.microserviceshop.dto.OrderDto;
import cz.dostalma.microserviceshop.dto.ProductDto;
import cz.dostalma.microserviceshop.model.*;
import cz.dostalma.microserviceshop.service.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class OrderFacadeImpl implements OrderFacade {

    private static final Logger logger
            = LoggerFactory.getLogger(OrderFacadeImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    CustomerService customerService;

    @Autowired
    AddressService addressService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Override
    public void createOrder(CheckoutOrderDto checkoutOrderDto) {
        Optional<Customer> customer = customerService.getCustomerByEmail(checkoutOrderDto.getCustomer().getEmail());
        Optional<Address> address = addressService.getAddressByDtoFields(checkoutOrderDto.getCustomer().getAddress());

        OrderDto orderDto = new OrderDto();
        orderDto.setCustomerId(customer.get().getId());
        orderDto.setShippingAddressId(address.get().getId());
        orderDto.setPaymentMethodId(checkoutOrderDto.getPaymentMethod().getId());
        orderDto.setProductMap(checkoutOrderDto.getProducts());
        orderDto.setTotalPrice(checkoutOrderDto.getProducts().entrySet().stream()
                .mapToDouble((Map.Entry<ProductDto, Integer> e) -> e.getValue() * e.getKey().getPrice())
                .sum());

        Order order = modelMapper.map(orderDto, Order.class);
        Set<ProductInOrder> products = new HashSet<>();
        for (Map.Entry<ProductDto, Integer> e : orderDto.getProductMap().entrySet()) {
            products.add(new ProductInOrder(
                    modelMapper.map(e.getKey(), Product.class),
                    order,
                    e.getValue())
            );
        }

        order.setProducts(products);

        for (ProductInOrder prodInOrder : products) {
            productService.saveProduct(prodInOrder.getProduct());
        }
        orderService.saveOrder(order);
    }

    @Override
    public List<OrderDto> getAllOrdersByCustomerEmail(String email) {
        return orderService.getAllOrdersByCustomerEmail(email).stream()
                .map(order -> {
                    OrderDto dto = modelMapper.map(order, OrderDto.class);
                    HashMap<ProductDto, Integer> productMap = new HashMap<>();
                    order.getProducts().forEach(pio ->  {
                        productMap.put(modelMapper.map(pio.getProduct(), ProductDto.class), pio.getQuantity());
                    });
                    dto.setProductMap(productMap);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
