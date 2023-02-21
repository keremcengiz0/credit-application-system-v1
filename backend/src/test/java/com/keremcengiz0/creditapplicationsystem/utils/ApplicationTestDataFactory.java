package com.keremcengiz0.creditapplicationsystem.utils;

import com.keremcengiz0.creditapplicationsystem.dtos.ApplicationDTO;
import com.keremcengiz0.creditapplicationsystem.dtos.CustomerDTO;
import com.keremcengiz0.creditapplicationsystem.enums.CreditResult;
import com.keremcengiz0.creditapplicationsystem.requests.ApplicationCreateRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class ApplicationTestDataFactory {

    // For Controller Test

    public static List<ApplicationDTO> prepareApplicationDTOForGetAll() {
        CustomerDTO customerDto1 = CustomerDTO.builder()
                .id(1L)
                .identityNumber("14725836914")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        ApplicationDTO applicationDTO1 = ApplicationDTO.builder()
                .id(1L)
                .salary(BigDecimal.valueOf(12000))
                .guarantee(BigDecimal.valueOf(8000))
                .creditScore(1200)
                .creditLimit(BigDecimal.valueOf(52000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto1)
                .build();

        CustomerDTO customerDto2 = CustomerDTO.builder()
                .id(2L)
                .identityNumber("14725836915")
                .firstName("Kerem")
                .lastName("Cengiz")
                .phoneNumber("1472583692")
                .birthDate(LocalDate.of(1999, 7, 31))
                .build();

        ApplicationDTO applicationDTO2 = ApplicationDTO.builder()
                .id(2L)
                .salary(BigDecimal.valueOf(20000))
                .guarantee(BigDecimal.valueOf(12000))
                .creditScore(800)
                .creditLimit(BigDecimal.valueOf(43000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto2)
                .build();

        CustomerDTO customerDto3 = CustomerDTO.builder()
                .id(3L)
                .identityNumber("14725836916")
                .firstName("Hilal")
                .lastName("Cengiz")
                .phoneNumber("1472583693")
                .birthDate(LocalDate.of(2001, 10, 7))
                .build();

        ApplicationDTO applicationDTO3 = ApplicationDTO.builder()
                .id(3L)
                .salary(BigDecimal.valueOf(8000))
                .guarantee(BigDecimal.valueOf(10000))
                .creditScore(600)
                .creditLimit(BigDecimal.valueOf(22000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto3)
                .build();

        CustomerDTO customerDto4 = CustomerDTO.builder()
                .id(4L)
                .identityNumber("14725836917")
                .firstName("Hulya")
                .lastName("Cengiz")
                .phoneNumber("1472583694")
                .birthDate(LocalDate.of(2003, 5, 25))
                .build();

        ApplicationDTO applicationDTO4 = ApplicationDTO.builder()
                .id(4L)
                .salary(BigDecimal.valueOf(6000))
                .guarantee(BigDecimal.valueOf(8000))
                .creditScore(400)
                .creditLimit(BigDecimal.valueOf(0))
                .creditResult(CreditResult.UNCONFIRMED)
                .customer(customerDto4)
                .build();

        List<ApplicationDTO> applicationDTOList = Arrays.asList(applicationDTO1, applicationDTO2, applicationDTO3, applicationDTO4);

        return applicationDTOList;
    }

    public static List<ApplicationDTO> prepareApplicationDTOForGetStatusWithParam() {
        CustomerDTO customerDto1 = CustomerDTO.builder()
                .id(1L)
                .identityNumber("14725836914")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        ApplicationDTO applicationDTO1 = ApplicationDTO.builder()
                .id(1L)
                .salary(BigDecimal.valueOf(12000))
                .guarantee(BigDecimal.valueOf(8000))
                .creditScore(1200)
                .creditLimit(BigDecimal.valueOf(52000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto1)
                .build();


        ApplicationDTO applicationDTO2 = ApplicationDTO.builder()
                .id(2L)
                .salary(BigDecimal.valueOf(20000))
                .guarantee(BigDecimal.valueOf(12000))
                .creditScore(800)
                .creditLimit(BigDecimal.valueOf(43000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto1)
                .build();

        ApplicationDTO applicationDTO3 = ApplicationDTO.builder()
                .id(3L)
                .salary(BigDecimal.valueOf(25000))
                .guarantee(BigDecimal.valueOf(20000))
                .creditScore(1100)
                .creditLimit(BigDecimal.valueOf(110000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto1)
                .build();


        List<ApplicationDTO> applicationDTOList = Arrays.asList(applicationDTO1, applicationDTO2, applicationDTO3);

        return applicationDTOList;
    }

    public static ApplicationCreateRequest prepareApplicationCreateRequest() {
        ApplicationCreateRequest applicationCreateRequest = ApplicationCreateRequest.builder()
                .salary(BigDecimal.valueOf(12000))
                .guarantee(BigDecimal.valueOf(8000))
                .build();

        return applicationCreateRequest;
    }

    public static ApplicationDTO prepareApplicationDTOForCreate() {
        CustomerDTO customerDto1 = CustomerDTO.builder()
                .id(1L)
                .identityNumber("14725836914")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        ApplicationDTO applicationDTO1 = ApplicationDTO.builder()
                .id(1L)
                .salary(BigDecimal.valueOf(12000))
                .guarantee(BigDecimal.valueOf(8000))
                .creditScore(1200)
                .creditLimit(BigDecimal.valueOf(52000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto1)
                .build();

        return applicationDTO1;
    }


    //For Service Test

    public static ApplicationDTO prepareApplicationDTOForUnconfirmed() {
        CustomerDTO customerDto = CustomerDTO.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .id(1L)
                .salary(BigDecimal.valueOf(4000))
                .guarantee(BigDecimal.valueOf(2000))
                .creditScore(300)
                .creditLimit(BigDecimal.valueOf(0))
                .creditResult(CreditResult.UNCONFIRMED)
                .customer(customerDto)
                .build();

        return applicationDTO;
    }

    public static ApplicationDTO prepareApplicationDTOForScoreBetween500And1000AndSalarySmallerThan5000ConfirmedApplication() {
        CustomerDTO customerDto = CustomerDTO.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .id(1L)
                .salary(BigDecimal.valueOf(4000))
                .guarantee(BigDecimal.valueOf(2000))
                .creditScore(600)
                .creditLimit(BigDecimal.valueOf(10200))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto)
                .build();

        return applicationDTO;
    }

    public static ApplicationDTO prepareApplicationDTOForScoreBetween500And1000AndSalarySmallerThan5000AndGuaranteeIsNullConfirmedApplication() {
        CustomerDTO customerDto = CustomerDTO.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .id(1L)
                .salary(BigDecimal.valueOf(4000))
                .creditScore(600)
                .creditLimit(BigDecimal.valueOf(10000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto)
                .build();

        return applicationDTO;
    }

    public static ApplicationDTO prepareApplicationDTOForScoreBetween500And1000AndSalaryBetween5000And10000ConfirmedApplication() {
        CustomerDTO customerDto = CustomerDTO.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .id(1L)
                .salary(BigDecimal.valueOf(8000))
                .guarantee(BigDecimal.valueOf(4000))
                .creditScore(600)
                .creditLimit(BigDecimal.valueOf(20800))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto)
                .build();

        return applicationDTO;
    }

    public static ApplicationDTO prepareApplicationDTOForScoreBetween500And1000AndSalaryOver10000ConfirmedApplication() {
        CustomerDTO customerDto = CustomerDTO.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .id(1L)
                .salary(BigDecimal.valueOf(12000))
                .guarantee(BigDecimal.valueOf(8000))
                .creditScore(700)
                .creditLimit(BigDecimal.valueOf(26000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto)
                .build();

        return applicationDTO;
    }

    public static ApplicationDTO prepareApplicationDTOForScoreOver1000ConfirmedApplication() {
        CustomerDTO customerDto = CustomerDTO.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .id(1L)
                .salary(BigDecimal.valueOf(15000))
                .guarantee(BigDecimal.valueOf(10000))
                .creditScore(1200)
                .creditLimit(BigDecimal.valueOf(70000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto)
                .build();

        return applicationDTO;
    }

    public static ApplicationDTO prepareApplicationDTOForUserNotFoundException() {
        CustomerDTO customerDto = CustomerDTO.builder()
                .id(1L)
                .identityNumber("12345678912")
                .firstName("Huseyin")
                .lastName("Cengiz")
                .phoneNumber("1472583691")
                .birthDate(LocalDate.of(1998, 3, 5))
                .build();

        ApplicationDTO applicationDTO = ApplicationDTO.builder()
                .id(1L)
                .salary(BigDecimal.valueOf(15000))
                .guarantee(BigDecimal.valueOf(10000))
                .creditScore(1200)
                .creditLimit(BigDecimal.valueOf(70000))
                .creditResult(CreditResult.CONFIRMED)
                .customer(customerDto)
                .build();

        return applicationDTO;
    }
}
