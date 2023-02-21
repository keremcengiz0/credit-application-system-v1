package com.keremcengiz0.creditapplicationsystem.services.abstracts;

import com.keremcengiz0.creditapplicationsystem.dtos.CustomerDTO;
import com.keremcengiz0.creditapplicationsystem.requests.CustomerCreateRequest;
import com.keremcengiz0.creditapplicationsystem.requests.CustomerUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {
    CustomerDTO save(CustomerCreateRequest customerCreateRequest);
    CustomerDTO update(CustomerUpdateRequest customerUpdateRequest);
    void delete(Long id);
    List<CustomerDTO> getAll();
    CustomerDTO getOneCustomer(Long id);
    CustomerDTO findCustomerByIdentityNumber(String identityNumber);
}
