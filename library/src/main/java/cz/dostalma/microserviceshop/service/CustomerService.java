package cz.dostalma.microserviceshop.service;

import cz.dostalma.microserviceshop.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerByEmail(String email);

    Optional<Customer> getCustomerById(Long id);

    void deleteCustomerByEmail(String email);

    Customer saveCustomer(Customer customer);
}
