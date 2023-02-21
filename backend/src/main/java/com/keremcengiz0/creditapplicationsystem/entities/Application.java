package com.keremcengiz0.creditapplicationsystem.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.keremcengiz0.creditapplicationsystem.enums.CreditResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@Entity
@Table(name = "applications")
@NoArgsConstructor
@AllArgsConstructor
public class Application extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "salary", nullable = false, precision=6, scale=0)
    private BigDecimal salary;

    @Column(name = "guarantee", nullable = true, precision=6, scale=0)
    private BigDecimal guarantee;

    @Column(name = "credit_score", nullable = false)
    private int creditScore;

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_result")
    private CreditResult creditResult;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
