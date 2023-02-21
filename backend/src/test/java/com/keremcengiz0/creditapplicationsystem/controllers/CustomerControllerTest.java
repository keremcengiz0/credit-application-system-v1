package com.keremcengiz0.creditapplicationsystem.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.keremcengiz0.creditapplicationsystem.dtos.CustomerDTO;
import com.keremcengiz0.creditapplicationsystem.requests.CustomerCreateRequest;
import com.keremcengiz0.creditapplicationsystem.requests.CustomerUpdateRequest;
import com.keremcengiz0.creditapplicationsystem.services.abstracts.CustomerService;
import com.keremcengiz0.creditapplicationsystem.utils.CustomerTestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;
    private ObjectMapper objectMapper;

    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        customerController = new CustomerController(customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void save() throws Exception {
        CustomerCreateRequest customerCreateRequest = CustomerTestDataFactory.prepareCustomerCreateRequest();

        CustomerDTO expectedResponse = CustomerTestDataFactory.prepareCustomerDTOForCreate();

        when(customerService.save(customerCreateRequest)).thenReturn(expectedResponse);

       MvcResult result = mockMvc.perform(post("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerCreateRequest)))
                        .andExpect(status().isOk())
                        .andReturn();

        CustomerDTO actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(),CustomerDTO.class);

        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);

        verify(customerService, times(1)).save(customerCreateRequest);
    }

    @Test
    void update() throws Exception {
        CustomerUpdateRequest customerUpdateRequest = CustomerTestDataFactory.prepareCustomerUpdateRequest();

        CustomerDTO expectedResponse = CustomerTestDataFactory.prepareCustomerDTOForUpdate();

        when(customerService.update(customerUpdateRequest)).thenReturn(expectedResponse);

        MvcResult result = mockMvc.perform(put("/api/v1/customers/{id}",customerUpdateRequest.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerUpdateRequest)))
                        .andExpect(status().isOk())
                        .andReturn();

        CustomerDTO actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(),CustomerDTO.class);

        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);

        verify(customerService, times(1)).update(customerUpdateRequest);

    }


    @Test
    void delete() throws Exception {

        Long customerId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/{id}", customerId))
                .andExpect(status().isOk())
                .andReturn();

        verify(customerService, times(1)).delete(customerId);

    }

    @Test
    void getAll() throws Exception {
        List<CustomerDTO> expectedCustomerDTOList = CustomerTestDataFactory.prepareCustomerDTOForGetAll();

        when(customerService.getAll()).thenReturn(expectedCustomerDTOList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        List<CustomerDTO> actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedCustomerDTOList);
        assertEquals(actualResponse.size(),expectedCustomerDTOList.size());
        verify(customerService, times(1)).getAll();
    }

    @Test
    void getOneCustomer() throws Exception {
        CustomerDTO expectedCustomerDTO = CustomerTestDataFactory.prepareCustomerDTOForGetAll().get(0);

        when(customerService.getOneCustomer(expectedCustomerDTO.getId())).thenReturn(expectedCustomerDTO);

       MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/get/{id}", expectedCustomerDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        CustomerDTO actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(),CustomerDTO.class);

        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedCustomerDTO);
        verify(customerService, times(1)).getOneCustomer(expectedCustomerDTO.getId());

    }
}