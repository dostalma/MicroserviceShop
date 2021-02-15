package cz.dostalma.microserviceshop.validation;

public class ExistingCustomerException extends RuntimeException {

    public ExistingCustomerException(String message) {
        super(message);
    }
}
