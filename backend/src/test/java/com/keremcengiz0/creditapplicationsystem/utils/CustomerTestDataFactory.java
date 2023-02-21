package com.keremcengiz0.creditapplicationsystem.utils;

import com.keremcengiz0.creditapplicationsystem.dtos.CustomerDTO;
import com.keremcengiz0.creditapplicationsystem.entities.Customer;
import com.keremcengiz0.creditapplicationsystem.requests.CustomerCreateRequest;
import com.keremcengiz0.creditapplicationsystem.requests.CustomerUpdateRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CustomerTestDataFactory {

    public static CustomerCreateRequest prepareCustomerCreateRequest() {
        return CustomerCreateRequest.builder()
                .identityNumber("12345678912")
                .firstName("Kerem")
                .lastName("Cengiz")
                .phoneNumber("1472583698")
                .birthDate(LocalDate.of(1999, 7, 31))
                .build();
    }

    public static Customer prepareCustomerForCreate() {
        return Customer.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Kerem")
                .lastName("Cengiz")
                .phoneNumber("1472583698")
                .birthDate(LocalDate.of(1999, 7, 31))
                .build();
    }

    public static CustomerDTO prepareCustomerDTOForCreate() {
        return CustomerDTO.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Kerem")
                .lastName("Cengiz")
                .phoneNumber("1472583698")
                .birthDate(LocalDate.of(1999, 7, 31))
                .build();
    }

    public static CustomerUpdateRequest prepareCustomerUpdateRequest() {
        return CustomerUpdateRequest.builder()
                .id(1L)
                .identityNumber("14725836914")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1999, 7, 31))
                .build();
    }

    public static Customer prepareCustomerForUpdate() {
        return Customer.builder()
                .id(1L)
                .identityNumber("14725836914")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1999, 7, 31))
                .build();
    }

    public static CustomerDTO prepareCustomerDTOForUpdate() {
        return CustomerDTO.builder()
                .id(1L)
                .identityNumber("14725836914")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1999, 7, 31))
                .build();
    }

    public static List<CustomerDTO> prepareCustomerDTOForGetAll() {

        CustomerDTO customerDto1 = CustomerDTO.builder()
                .id(1L)
                .identityNumber("14725836914")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        CustomerDTO customerDto2 = CustomerDTO.builder()
                .id(2L)
                .identityNumber("14725836915")
                .firstName("Kerem")
                .lastName("Cengiz")
                .phoneNumber("1472583692")
                .birthDate(LocalDate.of(1999, 7, 31))
                .build();

        CustomerDTO customerDto3 = CustomerDTO.builder()
                .id(3L)
                .identityNumber("14725836916")
                .firstName("Hilal")
                .lastName("Cengiz")
                .phoneNumber("1472583693")
                .birthDate(LocalDate.of(2001, 10, 7))
                .build();

        CustomerDTO customerDto4 = CustomerDTO.builder()
                .id(4L)
                .identityNumber("14725836917")
                .firstName("Hulya")
                .lastName("Cengiz")
                .phoneNumber("1472583694")
                .birthDate(LocalDate.of(2003, 5, 25))
                .build();

        List<CustomerDTO> customerDTOList = Arrays.asList(customerDto1 ,customerDto2, customerDto3, customerDto4);

        return customerDTOList;
    }

    public static Customer prepareCustomerForGetOneCustomer() {
        return Customer.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Kerem")
                .lastName("Cengiz")
                .phoneNumber("1472583698")
                .birthDate(LocalDate.of(1999, 7, 31))
                .build();
    }

    public static Customer prepareCustomerForDelete() {
        return Customer.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Kerem")
                .lastName("Cengiz")
                .phoneNumber("1472583698")
                .birthDate(LocalDate.of(1999, 7, 31))
                .build();
    }


}
