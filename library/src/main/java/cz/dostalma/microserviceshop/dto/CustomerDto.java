package cz.dostalma.microserviceshop.dto;

import cz.dostalma.microserviceshop.validation.FieldsValueMatch;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmationEmail",
                message = "Email addresses do not match!"
        )/*,
        @FieldsValueMatch(
                field = "password",
                fieldMatch = "confirmationPassword",
                message = "Passwords do not match!"
        )
        */
})
public class CustomerDto implements Serializable {

    private Long id;

    @NotBlank(message = "Full name is mandatory")
    private String fullName;

    @NotBlank(message = "Please enter a valid e-mail address")
    @Email(message = "Please enter a valid e-mail address")
    private String email;

    @NotBlank(message = "Please enter a valid e-mail address")
    @Email(message = "Please enter a valid e-mail address")
    private String confirmationEmail;

    //@NotBlank(message = "Please enter a valid password")
    private String password = "";

    //@NotBlank(message = "Please enter a valid confirmation password")
    private String confirmationPassword = "";

    @NotBlank(message = "Phone is mandatory")
    @Size(max = 9, message = "Phone number length must be between 0 and 9")
    private String phone;

    @Valid
    private AddressDto address;

    public CustomerDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerDto withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getConfirmationEmail() {
        return confirmationEmail;
    }

    public void setConfirmationEmail(String confirmationEmail) {
        this.confirmationEmail = confirmationEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerDto withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDto getAddress() {
        return address;
    }

    public void setAddress(AddressDto address) {
        this.address = address;
    }
}
