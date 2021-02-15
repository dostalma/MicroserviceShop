package cz.dostalma.microserviceshop.mapper;

import cz.dostalma.microserviceshop.dto.AddressDto;
import cz.dostalma.microserviceshop.model.Address;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("addressMapper")
public class AddressDtoToEntityMapperImpl implements DtoToEntityMapper {

    @Override
    public void map(Object dto, Object entity) {
        if (!(dto instanceof AddressDto) || !(entity instanceof Address)) {
            throw new IllegalArgumentException("Unsupported input objects received");
        }

        AddressDto dtoAddress = (AddressDto) dto;
        Address entityAddress = (Address) entity;

        entityAddress.setId(dtoAddress.getId() != null                   ? dtoAddress.getId()           : entityAddress.getId());
        entityAddress.setStreet(dtoAddress.getStreet() != null           ? dtoAddress.getStreet()       : entityAddress.getStreet());
        entityAddress.setHouseNumber(dtoAddress.getHouseNumber() != null ? dtoAddress.getHouseNumber()  : entityAddress.getHouseNumber());
        entityAddress.setCity(dtoAddress.getCity() != null               ? dtoAddress.getCity()         : entityAddress.getCity());
        entityAddress.setZipCode(dtoAddress.getZipCode() != null         ? dtoAddress.getZipCode()      : entityAddress.getZipCode());
        entityAddress.setCountry(dtoAddress.getCountry() != null         ? dtoAddress.getCountry()      : entityAddress.getCountry());
        entityAddress.setCustomerId(dtoAddress.getCustomerId() != null   ? dtoAddress.getCustomerId()      : entityAddress.getCustomerId());
    }
}
