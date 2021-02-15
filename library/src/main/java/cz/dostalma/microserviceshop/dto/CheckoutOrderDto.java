package cz.dostalma.microserviceshop.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

@Component
@Scope(
        value = WebApplicationContext.SCOPE_SESSION,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CheckoutOrderDto {

    private Map<ProductDto, Integer> products;

    private CustomerDto customer;

    private PaymentMethodDto paymentMethod;

    private boolean orderSubmitted;

    public void clearProductsAndPaymentMethod () {
        products = null;
        paymentMethod = null;
    }

    public Map<ProductDto, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductDto, Integer> products) {
        this.products = products;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public PaymentMethodDto getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodDto paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isOrderSubmitted() {
        return orderSubmitted;
    }

    public void setOrderSubmitted(boolean orderSubmitted) {
        this.orderSubmitted = orderSubmitted;
    }
}
