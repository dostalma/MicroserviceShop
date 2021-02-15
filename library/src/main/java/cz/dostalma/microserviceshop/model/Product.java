package cz.dostalma.microserviceshop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class Product implements Serializable {

    private static final long serialVersionUID = 666L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "CATALOG_IMAGE")
    private String catalogImageLink;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "product")
    Set<ProductInOrder> orders;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCatalogImageLink() {
        return catalogImageLink;
    }

    public void setCatalogImageLink(String catalogImageLink) {
        this.catalogImageLink = catalogImageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProductInOrder> getOrders() {
        return orders;
    }

    public void setOrders(Set<ProductInOrder> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", catalogImageLink='" + catalogImageLink + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class ProductBuilder {

        private String name;
        private Double price;
        private String catalogImageLink;
        private String description;

        public ProductBuilder() { }

        public ProductBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder withCatalogImageLink(String catalogImageLink) {
            this.catalogImageLink = catalogImageLink;
            return this;
        }

        public ProductBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setCatalogImageLink(catalogImageLink);
            product.setDescription(description);

            return product;
        }
    }
}