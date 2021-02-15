package cz.dostalma.microserviceshop.service;

import cz.dostalma.microserviceshop.dto.AddressDto;
import cz.dostalma.microserviceshop.model.Address;
import cz.dostalma.microserviceshop.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;


    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public List<Address> getAddressesByCustomerId(Long id) {
        return addressRepository.findAllByCustomerId(id);
    }

    @Override
    public Optional<Address> getAddressByDtoFields(AddressDto dto) {
        return addressRepository.findByStreetAndHouseNumberAndCityAndCountryAndZipCodeAndCustomerId(
                dto.getStreet(),
                dto.getHouseNumber(),
                dto.getCity(),
                dto.getCountry(),
                dto.getZipCode(),
                dto.getCustomerId()
        );
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.saveAndFlush(address);
    }
}
