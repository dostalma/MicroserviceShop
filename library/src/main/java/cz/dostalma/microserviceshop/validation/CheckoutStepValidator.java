package cz.dostalma.microserviceshop.validation;

import cz.dostalma.microserviceshop.CheckoutStep;
import cz.dostalma.microserviceshop.dto.CheckoutOrderDto;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class CheckoutStepValidator {

    public CheckoutStep validate(CheckoutStep step, CheckoutOrderDto orderDto) {
        if (step == null) {
            throw new IllegalArgumentException("CheckoutStep cannot be null");
        }

        switch (step) {
            case STEP_ADDRESS:
                break;
            case STEP_PAYMENT:
                if (orderDto.getProducts() == null || orderDto.getCustomer() == null || orderDto.getCustomer().getAddress() == null) {
                    return CheckoutStep.STEP_ADDRESS;
                }
                break;
            case STEP_CONFIRMATION:
                if (orderDto.getProducts() == null || orderDto.getCustomer() == null || orderDto.getCustomer().getAddress() == null) {
                    return CheckoutStep.STEP_ADDRESS;
                }
                if (orderDto.getPaymentMethod() == null) {
                    return CheckoutStep.STEP_PAYMENT;
                }
                break;
            case STEP_COMPLETE:
                if (orderDto.getProducts() == null || orderDto.getCustomer() == null || orderDto.getCustomer().getAddress() == null) {
                    return CheckoutStep.STEP_ADDRESS;
                }
                if (orderDto.getPaymentMethod() == null) {
                    return CheckoutStep.STEP_PAYMENT;
                }
                /* @TODO */
                break;
            default:
                throw new IllegalArgumentException("Unsupported CheckoutStep found");
        }

        return step;
    }

}
