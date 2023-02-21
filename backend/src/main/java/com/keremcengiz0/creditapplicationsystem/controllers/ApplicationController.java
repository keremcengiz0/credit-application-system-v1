package com.keremcengiz0.creditapplicationsystem.controllers;

import com.keremcengiz0.creditapplicationsystem.dtos.ApplicationDTO;
import com.keremcengiz0.creditapplicationsystem.requests.ApplicationCreateRequest;
import com.keremcengiz0.creditapplicationsystem.services.abstracts.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> getAll() {
        log.info("ApplicationController: A request has been received to list all applications.");
        return new ResponseEntity<>(this.applicationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get-status")
    public ResponseEntity<List<ApplicationDTO>> getStatusWithParam( @RequestParam("identityNumber") String identityNumber,
                                                                   @RequestParam("birthDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate) {
        log.info("ApplicationController: A request has been received to list one application with identity number and birthdate information.");
        return new ResponseEntity<>(this.applicationService.getStatusWithParam(identityNumber, birthDate), HttpStatus.OK);
    }

    @PostMapping("/{identityNumber}")
    public ResponseEntity<ApplicationDTO> createOneApplication(@RequestBody ApplicationCreateRequest applicationCreateRequest,
                                                               @PathVariable(value = "identityNumber") @Valid String identityNumber) {
        log.info("ApplicationController: A request has been received to create one application.");
        return new ResponseEntity<>(this.applicationService.makeAnApplication(applicationCreateRequest, identityNumber), HttpStatus.OK);
    }

}
