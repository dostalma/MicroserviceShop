package cz.dostalma.microserviceshop.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private String catalogImageLink;
    private String description;

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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", catalogImageLink='" + catalogImageLink + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class ProductDTOBuilder {

        private Long id;
        private String name;
        private Double price;
        private String catalogImageLink;
        private String description;

        public ProductDTOBuilder() { }

        public ProductDto.ProductDTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public ProductDto.ProductDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ProductDto.ProductDTOBuilder withPrice(Double price) {
            this.price = price;
            return this;
        }

        public ProductDto.ProductDTOBuilder withCatalogImageLink(String catalogImageLink) {
            this.catalogImageLink = catalogImageLink;
            return this;
        }

        public ProductDto.ProductDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ProductDto build() {
            ProductDto productDto = new ProductDto();
            productDto.setId(id);
            productDto.setName(name);
            productDto.setPrice(price);
            productDto.setCatalogImageLink(catalogImageLink);
            productDto.setDescription(description);

            return productDto;
        }
    }
}
