package cz.dostalma.microserviceshop.service;

import cz.dostalma.microserviceshop.model.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    List<PaymentMethod> getAllPaymentMethods();

    Optional<PaymentMethod> getPaymentMethodById(Long id);
}
