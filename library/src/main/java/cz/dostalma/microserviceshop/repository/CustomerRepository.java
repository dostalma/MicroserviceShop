package cz.dostalma.microserviceshop.repository;

import cz.dostalma.microserviceshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    void deleteByEmail(String email);
}
