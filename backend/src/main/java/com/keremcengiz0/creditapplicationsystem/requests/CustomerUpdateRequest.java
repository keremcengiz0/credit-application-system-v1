package com.keremcengiz0.creditapplicationsystem.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerUpdateRequest {
    private Long id;

    @NotBlank(message = "identity_number is required!")
    @Pattern(regexp = "(^[0-9]{11}$)", message = "The ID number must be 11 characters!")
    @Pattern(regexp = "(^\\d*[02468]$)", message = "The last digit of the ID number must be an even number!")
    private String identityNumber;

    @Size(min = 3, max = 50, message = "The First Name must contain between 3-50 characters!")
    @Pattern(regexp = "(^[a-zA-Z]{3,50}$)", message = "The First Name must be of characters!")
    private String firstName;

    @Size(min = 2, max = 50, message = "The First Name must contain between 3-50 characters!")
    @Pattern(regexp = "(^[a-zA-Z]{3,50}$)", message = "The First Name must be of characters!")
    private String lastName;

    @NotBlank(message = "Phone Number is required!")
    @Pattern(regexp = "(^[0-9]{10}$)", message = "The phone number must consist of numbers and must be 10 characters (not including 0)!")
    private String phoneNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
}
