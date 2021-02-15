package cz.dostalma.microserviceshop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ORDERS_ID")
    private Long id;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    @Column(name = "SHIPPING_ADDRESS_ID")
    private Long shippingAddressId;

    @Column(name = "PAYMENT_METHOD_ID")
    private Long paymentMethodId;

    @Column(name = "TOTAL_PRICE")
    private Double totalPrice;

    @OneToMany(mappedBy = "order", targetEntity = ProductInOrder.class)
    /*
    @JoinTable(
            name = "PRODUCT_ORDERS",
            joinColumns = @JoinColumn(name = "ORDERS_ID", referencedColumnName = "ORDERS_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    )
    */
    private Set<ProductInOrder> products;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Set<ProductInOrder> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductInOrder> products) {
        this.products = products;
    }

    public static class OrderBuilder {

        private Long customerId;
        private Long shippingAddressId;
        private Long paymentMethodId;
        private Double totalPrice;
        private Set<ProductInOrder> products;

        public OrderBuilder() { }

        public Order.OrderBuilder withCustomerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Order.OrderBuilder withShippingAddressId(Long shippingAddressId) {
            this.shippingAddressId = shippingAddressId;
            return this;
        }

        public Order.OrderBuilder withPaymentMethodId(Long paymentMethodId) {
            this.paymentMethodId = paymentMethodId;
            return this;
        }

        public Order.OrderBuilder withTotalPrice(Double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Order.OrderBuilder withProducts(Set<ProductInOrder> products) {
            this.products = products;
            return this;
        }

        public Order build() {
            Order order = new Order();
            order.setCustomerId(customerId);
            order.setShippingAddressId(shippingAddressId);
            order.setPaymentMethodId(paymentMethodId);
            order.setTotalPrice(totalPrice);
            products.forEach( prod -> prod.setOrder(order) );
            order.setProducts(products);

            return order;
        }
    }
}
