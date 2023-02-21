package com.keremcengiz0.creditapplicationsystem.services.concretes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import com.keremcengiz0.creditapplicationsystem.utils.CustomerTestDataFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(customerRepository, customerMapper);
    }

    @Test
    void save_WhenProperInputIsGiven_ThenShouldSuccess() {
        CustomerCreateRequest customerCreateRequest = CustomerTestDataFactory.prepareCustomerCreateRequest();
        CustomerDTO expectedResponse = CustomerTestDataFactory.prepareCustomerDTOForCreate();

        when(customerRepository.existsByIdentityNumber(customerCreateRequest.getIdentityNumber())).thenReturn(false);
        when(customerRepository.existsByPhoneNumber(customerCreateRequest.getPhoneNumber())).thenReturn(false);

        CustomerDTO actualResponse = customerService.save(customerCreateRequest);

        assertEquals(actualResponse.getIdentityNumber(), expectedResponse.getIdentityNumber());
        assertEquals(actualResponse.getFirstName(), expectedResponse.getFirstName());
        assertEquals(actualResponse.getLastName(), expectedResponse.getLastName());
        assertEquals(actualResponse.getPhoneNumber(), expectedResponse.getPhoneNumber());
        assertEquals(actualResponse.getBirthDate(), expectedResponse.getBirthDate());

        verify(customerRepository, times(1)).existsByIdentityNumber(customerCreateRequest.getIdentityNumber());
        verify(customerRepository, times(1)).existsByPhoneNumber(customerCreateRequest.getPhoneNumber());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void save_WhenIdentityNumberAlreadyExists_ThenShouldThrowIdentityNumberIsAlreadyExistException() {
        CustomerCreateRequest customerCreateRequest = CustomerTestDataFactory.prepareCustomerCreateRequest();
        CustomerDTO expectedResponse = CustomerTestDataFactory.prepareCustomerDTOForCreate();

        when(customerRepository.existsByIdentityNumber(expectedResponse.getIdentityNumber())).thenReturn(true);

        try {
            customerService.save(customerCreateRequest);
            fail("Expected IdentityNumberIsAlreadyExistException was not thrown.");
        } catch (IdentityNumberIsAlreadyExistException ex) {
            assertThat(ex.getMessage()).isEqualTo(expectedResponse.getIdentityNumber() + " --> This id number already exists.");
        }
    }

    @Test
    void save_WhenPhoneNumberAlreadyExists_ThenShouldThrowPhoneNumberIsAlreadyExistException() {
        CustomerCreateRequest customerCreateRequest = CustomerTestDataFactory.prepareCustomerCreateRequest();
        CustomerDTO expectedResponse = CustomerTestDataFactory.prepareCustomerDTOForCreate();

        when(customerRepository.existsByPhoneNumber(expectedResponse.getPhoneNumber())).thenReturn(true);

        try {
            customerService.save(customerCreateRequest);
            fail("Expected PhoneNumberIsAlreadyExistException was not thrown.");
        } catch (PhoneNumberIsAlreadyExistException ex) {
            assertThat(ex.getMessage()).isEqualTo(expectedResponse.getPhoneNumber() + " --> This phone number already exists.");
        }
    }

    @Test
    void update_WhenProperInputIsGiven_ThenShouldSuccess() {
        CustomerUpdateRequest customerUpdateRequest = CustomerTestDataFactory.prepareCustomerUpdateRequest();
        CustomerDTO expectedResponse = CustomerTestDataFactory.prepareCustomerDTOForUpdate();
        Customer customer = CustomerTestDataFactory.prepareCustomerForUpdate();

        when(customerRepository.findById(customerUpdateRequest.getId())).thenReturn(Optional.ofNullable(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        CustomerDTO actualResponse = customerService.update(customerUpdateRequest);

        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);

        verify(customerRepository, times(1)).findById(customerUpdateRequest.getId());
        verify(customerRepository, times(1)).save(any(Customer.class));

    }

    @Test
    void update_WhenIdentityNumberAlreadyExists_ThenShouldThrowException() {
        CustomerUpdateRequest customerUpdateRequest = CustomerTestDataFactory.prepareCustomerUpdateRequest();
        customerUpdateRequest.setIdentityNumber("57487898745");
        CustomerDTO expectedResponse = CustomerTestDataFactory.prepareCustomerDTOForUpdate();
        expectedResponse.setIdentityNumber(customerUpdateRequest.getIdentityNumber());

        Customer customer = CustomerTestDataFactory.prepareCustomerForUpdate();

        when(customerRepository.findById(expectedResponse.getId())).thenReturn(Optional.ofNullable(customer));
        when(customerRepository.existsByIdentityNumber(expectedResponse.getIdentityNumber())).thenReturn(true);

        assertThrows(IdentityNumberIsAlreadyExistException.class, () -> customerService.update(customerUpdateRequest));
    }

    @Test
    void update_WhenPhoneNumberAlreadyExists_ThenShouldThrowException() {
        CustomerUpdateRequest customerUpdateRequest = CustomerTestDataFactory.prepareCustomerUpdateRequest();
        customerUpdateRequest.setPhoneNumber("4781254887");
        CustomerDTO expectedResponse = CustomerTestDataFactory.prepareCustomerDTOForUpdate();
        expectedResponse.setPhoneNumber(customerUpdateRequest.getPhoneNumber());

        Customer customer = CustomerTestDataFactory.prepareCustomerForUpdate();

        when(customerRepository.findById(expectedResponse.getId())).thenReturn(Optional.ofNullable(customer));
        when(customerRepository.existsByPhoneNumber(expectedResponse.getPhoneNumber())).thenReturn(true);

        assertThrows(PhoneNumberIsAlreadyExistException.class, () -> customerService.update(customerUpdateRequest));
    }

    @Test
    void delete_WhenProperIdParameterIsGiven_ThenShouldDeleteCustomer() {
        Customer customer = CustomerTestDataFactory.prepareCustomerForDelete();
        Long customerId = customer.getId();

        customerService.delete(customerId);
        verify(customerRepository, times(1)).deleteById(customerId);

    }

    @Test
    void getAll_WhenProperInputIsGiven_ThenShouldReturnCustomerDTOList() {
        List<CustomerDTO> customerDTOList = CustomerTestDataFactory.prepareCustomerDTOForGetAll();
        List<Customer> customerList = customerMapper.fromCustomerDtoListToCustomerList(customerDTOList);

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> actualCustomerDtoList = customerService.getAll();

        assertEquals(customerList.size(), actualCustomerDtoList.size());
        assertEquals(customerDTOList, actualCustomerDtoList);

    }

    @Test
    void getOneCustomer_WhenCustomerExists_ThenShouldReturnCustomerDTO() {
        Customer expectedCustomer = CustomerTestDataFactory.prepareCustomerForGetOneCustomer();

        when(customerRepository.findById(expectedCustomer.getId())).thenReturn(Optional.of(expectedCustomer));

        CustomerDTO actualResponse = customerService.getOneCustomer(expectedCustomer.getId());

        assertNotNull(actualResponse);
        assertEquals(expectedCustomer.getId(), actualResponse.getId());
        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedCustomer);

    }

    @Test
    void getOneCustomer_WhenCustomerNotFound_ThenShouldThrowNotFoundException() {
        Long customerId = 12L;

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> customerService.getOneCustomer(customerId));
    }

    @Test
    void findCustomerByIdentityNumber_WhenCustomerExists_ThenShouldReturnCustomerDTO() {
        Customer expectedCustomer = CustomerTestDataFactory.prepareCustomerForGetOneCustomer();

        when(customerRepository.findCustomerByIdentityNumber(expectedCustomer.getIdentityNumber())).thenReturn(Optional.of(expectedCustomer));

        CustomerDTO actualResponse = customerService.findCustomerByIdentityNumber(expectedCustomer.getIdentityNumber());

        assertNotNull(actualResponse);
        assertEquals(expectedCustomer.getIdentityNumber(), actualResponse.getIdentityNumber());
        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedCustomer);
    }

    @Test
    void findCustomerByIdentityNumber_WhenCustomerNotFound_ThenShouldThrowNotFoundException() {
        String customerIdentityNumber = "55555555555" ;

        when(customerRepository.findCustomerByIdentityNumber(customerIdentityNumber)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> customerService.findCustomerByIdentityNumber(customerIdentityNumber));
    }

}