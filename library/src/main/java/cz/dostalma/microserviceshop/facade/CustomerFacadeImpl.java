package cz.dostalma.microserviceshop.facade;

import cz.dostalma.microserviceshop.dto.AddressDto;
import cz.dostalma.microserviceshop.dto.CustomerDto;
import cz.dostalma.microserviceshop.dto.PaymentMethodDto;
import cz.dostalma.microserviceshop.mapper.DtoToEntityMapper;
import cz.dostalma.microserviceshop.model.Address;
import cz.dostalma.microserviceshop.model.Customer;
import cz.dostalma.microserviceshop.model.PaymentMethod;
import cz.dostalma.microserviceshop.service.AddressService;
import cz.dostalma.microserviceshop.service.CustomerService;
import cz.dostalma.microserviceshop.service.PaymentService;
import cz.dostalma.microserviceshop.validation.ExistingCustomerException;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerFacadeImpl implements CustomerFacade {

    @Autowired
    CustomerService customerService;

    @Autowired
    AddressService addressService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    @Qualifier("customerMapper")
    DtoToEntityMapper customerEntityMapper;

    @Autowired
    @Qualifier("addressMapper")
    DtoToEntityMapper addressEntityMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customer -> modelMapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        return modelMapper.map(customerService.getCustomerById(id).orElse(null), CustomerDto.class);
    }

    @Override
    public CustomerDto getCustomerByEmail(String email) {
        return modelMapper.map(customerService.getCustomerByEmail(email).orElse(null), CustomerDto.class);
    }

    @Override
    public void registerNewCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            return;
        }

        Customer existingEntity = customerService.getCustomerByEmail(customerDto.getEmail()).orElse(new Customer());
        if (existingEntity != null && StringUtils.isNotEmpty(existingEntity.getPassword())) {
            throw new ExistingCustomerException("A customer with the same email already exists.");
        }

        registerCustomer(customerDto, existingEntity);
    }

    @Override
    public void registerCustomer(CustomerDto dto, Customer entity) {
        if (dto == null) {
            return;
        }

        Customer existingEntity = entity;
        if (entity == null) {
            existingEntity = customerService.getCustomerByEmail(dto.getEmail()).orElse(new Customer());
        }

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        customerEntityMapper.map(dto, existingEntity);
        customerService.saveCustomer(existingEntity);

        AddressDto addressDto = dto.getAddress();
        if (addressDto != null) {
            Long customerId = customerService.getCustomerByEmail(dto.getEmail()).get().getId();
            addressDto.setCustomerId(customerId);
            Optional<Address> addressEntityOpt = addressService.getAddressByDtoFields(addressDto);
            if (addressEntityOpt.isPresent()) {
                return;
            }
            Address addressEntity = new Address();
            addressEntityMapper.map(addressDto, addressEntity);

            addressService.saveAddress(addressEntity);
        }
    }

    @Override
    public void updateCustomer(CustomerDto dto) {
        Customer entity = customerService.getCustomerByEmail(dto.getEmail()).orElse(null);

        if (entity != null) {
            Long id = entity.getId();
            entity =  modelMapper.map(dto, Customer.class);
            entity.setId(id);
            customerService.saveCustomer(entity);
        }
    }

    @Override
    public AddressDto getAddressById(Long id) {
        return modelMapper.map(addressService.getAddressById(id).orElse(null), AddressDto.class);
    }

    @Override
    public List<PaymentMethodDto> getAllPaymentMethods() {
        return paymentService.getAllPaymentMethods().stream()
                .map(paymentMethod -> modelMapper.map(paymentMethod, PaymentMethodDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentMethodDto getPaymentMethodById(Long id) {
        return modelMapper.map(paymentService.getPaymentMethodById(id).orElse(new PaymentMethod()), PaymentMethodDto.class);
    }
}
