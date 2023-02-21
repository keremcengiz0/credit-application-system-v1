package com.keremcengiz0.creditapplicationsystem.dtos;

import com.keremcengiz0.creditapplicationsystem.enums.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {
    private Long id;
    private BigDecimal salary;
    private BigDecimal guarantee;
    private int creditScore;
    private BigDecimal creditLimit;
    private CreditResult creditResult;
    private CustomerDTO customer;
}
