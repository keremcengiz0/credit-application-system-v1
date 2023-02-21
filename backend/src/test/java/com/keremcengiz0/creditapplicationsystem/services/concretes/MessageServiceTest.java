package com.keremcengiz0.creditapplicationsystem.services.concretes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;



    @Test
    void getMessage() {
        String successfulMessage  = messageService.getMessage(true);
        String unsuccessfulMessage  = messageService.getMessage(false);

        assertEquals("Credit application has been approved!", successfulMessage);
        assertEquals("Credit application was not approved!", unsuccessfulMessage);
    }

    @Test
    void sendSms() {
        Boolean successResult = messageService.sendSms("5551234567", true);
        assertTrue(successResult);

        Boolean unsuccessfulResult = messageService.sendSms("5551234567", false);
        assertTrue(unsuccessfulResult);
    }
}