package com.keremcengiz0.creditapplicationsystem.dtos;

import com.keremcengiz0.creditapplicationsystem.enums.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResultDTO {
    private BigDecimal creditLimit;
    private CreditResult creditResult;
}
