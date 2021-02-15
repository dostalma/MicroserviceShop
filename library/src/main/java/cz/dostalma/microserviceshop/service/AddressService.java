package cz.dostalma.microserviceshop.service;


import cz.dostalma.microserviceshop.dto.AddressDto;
import cz.dostalma.microserviceshop.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> getAllAddresses();

    List<Address> getAddressesByCustomerId(Long id);

    Optional<Address> getAddressByDtoFields(AddressDto dto);

    Optional<Address> getAddressById(Long id);

    Address saveAddress(Address address);
}
