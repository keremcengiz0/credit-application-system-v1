package com.keremcengiz0.creditapplicationsystem.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.keremcengiz0.creditapplicationsystem.dtos.ApplicationDTO;
import com.keremcengiz0.creditapplicationsystem.requests.ApplicationCreateRequest;
import com.keremcengiz0.creditapplicationsystem.services.abstracts.ApplicationService;
import com.keremcengiz0.creditapplicationsystem.utils.ApplicationTestDataFactory;
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

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
class ApplicationControllerTest {

    @Mock
    private ApplicationService applicationService;
    private ObjectMapper objectMapper;
    ApplicationController applicationController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        applicationController = new ApplicationController(applicationService);
        mockMvc = MockMvcBuilders.standaloneSetup(applicationController).build();
    }

    @Test
    void getAll() throws Exception {
        List<ApplicationDTO> expectedApplicationDTOList = ApplicationTestDataFactory.prepareApplicationDTOForGetAll();

        when(applicationService.getAll()).thenReturn(expectedApplicationDTOList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/applications")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<ApplicationDTO> actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedApplicationDTOList);
        assertEquals(actualResponse.size(), expectedApplicationDTOList.size());
        verify(applicationService, times(1)).getAll();
    }

    @Test
    void getStatusWithParam() throws Exception {
        List<ApplicationDTO> expectedApplicationDTOList = ApplicationTestDataFactory.prepareApplicationDTOForGetStatusWithParam();

        String identityNumberParam = expectedApplicationDTOList.get(0).getCustomer().getIdentityNumber();
        LocalDate birthDateParam = expectedApplicationDTOList.get(0).getCustomer().getBirthDate();
        String birthDate = expectedApplicationDTOList.get(0).getCustomer().getBirthDate().toString();

        when(applicationService.getStatusWithParam(identityNumberParam, birthDateParam)).thenReturn(expectedApplicationDTOList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/applications/get-status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("identityNumber",identityNumberParam)
                        .param("birthDate", birthDate))
                        .andExpect(status().isOk())
                        .andReturn();

        List<ApplicationDTO> actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedApplicationDTOList);
        assertEquals(actualResponse.size(), expectedApplicationDTOList.size());
        verify(applicationService, times(1)).getStatusWithParam(identityNumberParam,birthDateParam);

    }

    @Test
    void createOneApplication() throws Exception {
        ApplicationCreateRequest applicationCreateRequest = ApplicationTestDataFactory.prepareApplicationCreateRequest();

        ApplicationDTO expectedResponse = ApplicationTestDataFactory.prepareApplicationDTOForCreate();
        String identityNumber = expectedResponse.getCustomer().getIdentityNumber();

        when(applicationService.makeAnApplication(applicationCreateRequest,identityNumber)).thenReturn(expectedResponse);

        MvcResult result = mockMvc.perform(post("/api/v1/applications/{identityNumber}", identityNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(applicationCreateRequest)))
                        .andExpect(status().isOk())
                        .andReturn();

        ApplicationDTO actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(),ApplicationDTO.class);
        assertThat(actualResponse).usingRecursiveComparison().isEqualTo(expectedResponse);
        verify(applicationService, times(1)).makeAnApplication(applicationCreateRequest, identityNumber);

    }
}