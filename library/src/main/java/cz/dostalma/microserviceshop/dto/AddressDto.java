package cz.dostalma.microserviceshop.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class AddressDto implements Serializable {

    private Long id;

    @NotBlank(message = "Street is mandatory")
    private String street;

    @NotBlank(message = "House number is mandatory")
    private String houseNumber;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "Zip code is mandatory")
    @Size(min=5, max=5)
    private String zipCode;

    @NotBlank(message = "Country is mandatory")
    private String country;

    private Long customerId;

    public AddressDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
