package cz.dostalma.microserviceshop.facade;

import cz.dostalma.microserviceshop.dto.AddressDto;
import cz.dostalma.microserviceshop.dto.CustomerDto;
import cz.dostalma.microserviceshop.dto.PaymentMethodDto;
import cz.dostalma.microserviceshop.model.Customer;

import java.util.List;

public interface CustomerFacade {

    /**
     * Get all existing customers
     * @return List of Customer dto objects
     */
    List<CustomerDto> getAllCustomers();

    /**
     * Get an existing customer by id
     * @param id Id of an existing customer
     * @return Customer dto object
     */
    CustomerDto getCustomerById(Long id);

    /**
     * Get an existing customer by email
     * @param email Email of an existing customer
     * @return Customer dto object
     */
    CustomerDto getCustomerByEmail(String email);

    /**
     * Register a new customer. Throws an exception if there is already a customer with the same email and filled password.
     * @param customer Customer dto object
     */
    void registerNewCustomer(CustomerDto customer);

    /**
     * Register a new customer. If a customer with the same email already exists, an update is done instead.
     * Accepts optional Customer entity param in order to not duplicate search for an existing entity if it is already provided.
     * @param customer Customer dto object
     * @param customer Optional Customer entity
     */
    void registerCustomer(CustomerDto customer, Customer entity);

    /**
     * Perform an update of customer
     * @param customer Customer dto object to update
     */
    void updateCustomer(CustomerDto customer);

    /**
     * Get an address by its id
     * @param id Id of the address
     * @return Address dto object
     */
    AddressDto getAddressById(Long id);

    /**
     * Get all payment methods
     * @return List of payment methods
     */
    List<PaymentMethodDto> getAllPaymentMethods();

    /**
     * Get Payment method by its id
     * @param id Id of the payment method
     * @return Payment method dto object
     */
    PaymentMethodDto getPaymentMethodById(Long id);
}
