package com.keremcengiz0.creditapplicationsystem.services.concretes;

import com.keremcengiz0.creditapplicationsystem.dtos.CustomerDTO;
import com.keremcengiz0.creditapplicationsystem.entities.Customer;
import com.keremcengiz0.creditapplicationsystem.exceptions.IdentityNumberIsAlreadyExistException;
import com.keremcengiz0.creditapplicationsystem.exceptions.NotFoundException;
import com.keremcengiz0.creditapplicationsystem.exceptions.PhoneNumberIsAlreadyExistException;
import com.keremcengiz0.creditapplicationsystem.mappers.CustomerMapper;
import com.keremcengiz0.creditapplicationsystem.repositories.CustomerRepository;
import com.keremcengiz0.creditapplicationsystem.requests.CustomerCreateRequest;
import com.keremcengiz0.creditapplicationsystem.requests.CustomerUpdateRequest;
import com.keremcengiz0.creditapplicationsystem.services.abstracts.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    protected final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDTO save(CustomerCreateRequest customerCreateRequest) {
        CustomerDTO customerDTO = this.customerMapper.fromCustomerCreateRequestToCustomerDto(customerCreateRequest);
        if(this.customerRepository.existsByIdentityNumber(customerDTO.getIdentityNumber())) {
            throw new IdentityNumberIsAlreadyExistException(customerDTO.getIdentityNumber() + " --> This id number already exists.");
        }
        if(this.customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
            throw new PhoneNumberIsAlreadyExistException(customerDTO.getPhoneNumber() + " --> This phone number already exists.");
        }

        Customer customer = this.customerMapper.fromCustomerDtoToCustomer(customerDTO);
        this.customerRepository.save(customer);
        log.info("ICustomerService: Customer has been created.");

        return this.customerMapper.fromCustomerToCustomerDto(customer);
    }

    @Override
    public CustomerDTO update(CustomerUpdateRequest customerUpdateRequest) {
        CustomerDTO customerDTO = this.customerMapper.fromCustomerUpdateRequestToCustomerDto(customerUpdateRequest);

        Customer toUpdateCustomer = this.customerRepository.findById(customerDTO.getId()).get();

        if(!toUpdateCustomer.getIdentityNumber().equals(customerDTO.getIdentityNumber())) {
            if(this.customerRepository.existsByIdentityNumber(customerDTO.getIdentityNumber())) {
                throw new IdentityNumberIsAlreadyExistException(customerDTO.getIdentityNumber() + " --> This identity number already exists.");
            }
        }

        if(!toUpdateCustomer.getPhoneNumber().equals(customerDTO.getPhoneNumber())) {
            if(this.customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())) {
                throw new PhoneNumberIsAlreadyExistException(customerDTO.getPhoneNumber() + " --> This phone number already exists.");
            }
        }

        toUpdateCustomer = this.customerMapper.fromCustomerDtoToCustomer(customerDTO);
        this.customerRepository.save(toUpdateCustomer);
        log.info("ICustomerService: Customer has been updated.");
        return this.customerMapper.fromCustomerToCustomerDto(toUpdateCustomer);
    }

    @Override
    public void delete(Long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<Customer> customers = this.customerRepository.findAll();
        return this.customerMapper.fromCustomerListToCustomerDtoList(customers);
    }

    @Override
    public CustomerDTO getOneCustomer(Long id) {
        Optional<Customer> optionalCustomer = this.customerRepository.findById(id);

        if(!optionalCustomer.isPresent()) {
            throw new NotFoundException("Customer with id " + id + " could not be found!");
        }
        Customer customer = optionalCustomer.get();

        return this.customerMapper.fromCustomerToCustomerDto(customer);
    }

    @Override
    public CustomerDTO findCustomerByIdentityNumber(String identityNumber) {
        Optional<Customer> optionalCustomer = this.customerRepository.findCustomerByIdentityNumber(identityNumber);

        if(!optionalCustomer.isPresent()) {
            throw new NotFoundException("Customer with id number " + identityNumber + " could not be found!");
        }

        Customer customer = optionalCustomer.get();

        return this.customerMapper.fromCustomerToCustomerDto(customer);
    }

}
