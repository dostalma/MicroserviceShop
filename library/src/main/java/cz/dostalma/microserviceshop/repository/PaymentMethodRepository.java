package cz.dostalma.microserviceshop.repository;

import cz.dostalma.microserviceshop.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
}
