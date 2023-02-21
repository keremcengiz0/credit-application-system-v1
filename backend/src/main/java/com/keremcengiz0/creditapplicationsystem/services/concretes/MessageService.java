package com.keremcengiz0.creditapplicationsystem.services.concretes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class MessageService {
    private static final String SUCCESSFUL_MESSAGE = "Credit application has been approved!";
    private static final String UNSUCCESSFUL_MESSAGE = "Credit application was not approved!";

    public String getMessage(Boolean status) {
        return status ? SUCCESSFUL_MESSAGE : UNSUCCESSFUL_MESSAGE;
    }

    public Boolean sendSms(String phoneNumber, Boolean status, BigDecimal creditLimit) {
        getMessage(status);
        log.info("MessageService: " + getMessage(status));
        return true;
    }

}
