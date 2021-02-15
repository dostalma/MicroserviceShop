package cz.dostalma.microserviceshop.mapper;

import cz.dostalma.microserviceshop.dto.CustomerDto;
import cz.dostalma.microserviceshop.model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("customerMapper")
public class CustomerDtoToEntityMapperImpl implements DtoToEntityMapper {

    @Override
    public void map(Object dto, Object entity) {
        if (!(dto instanceof CustomerDto) || !(entity instanceof Customer)) {
            throw new IllegalArgumentException("Unsupported input objects received");
        }

        CustomerDto dtoCust = (CustomerDto) dto;
        Customer entityCust = (Customer) entity;

        entityCust.setId(dtoCust.getId() != null                ? dtoCust.getId()       : entityCust.getId());
        entityCust.setEmail(dtoCust.getEmail() != null          ? dtoCust.getEmail()    : entityCust.getEmail());
        entityCust.setPassword(dtoCust.getPassword() != null    ? dtoCust.getPassword() : entityCust.getPassword());
        entityCust.setFullName(dtoCust.getFullName() != null    ? dtoCust.getFullName() : entityCust.getFullName());
        entityCust.setPhone(dtoCust.getPhone() != null          ? dtoCust.getPhone()    : entityCust.getPhone());
    }
}
