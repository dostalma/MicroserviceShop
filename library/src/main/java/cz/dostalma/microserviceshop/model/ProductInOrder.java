package cz.dostalma.microserviceshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCT_ORDERS")
public class ProductInOrder implements Serializable {

    private static final long serialVersionUID = 98l;

    @EmbeddedId
    ProductsInOrderKey id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "ORDERS_ID")
    private Order order;

    @Column(name = "QUANTITY")
    private Integer quantity;

    public ProductInOrder() {}

    public ProductInOrder(Product product, Order order, Integer quantity) {
        this.product = product;
        this.order = order;
        this.quantity = quantity;
    }

    public ProductsInOrderKey getId() {
        return id;
    }

    public void setId(ProductsInOrderKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Embeddable
    public static class ProductsInOrderKey implements Serializable {

        @Column(name = "PRODUCT_ID", table = "PRODUCT")
        Long productId;

        @Column(name = "ORDERS_ID", table = "ORDERS")
        Long orderId;

        public ProductsInOrderKey(Long productId, Long orderId) {
            this.productId = productId;
            this.orderId = orderId;
        }

        public ProductsInOrderKey() {}

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }
    }
}
