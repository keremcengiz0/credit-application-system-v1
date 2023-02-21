package com.keremcengiz0.creditapplicationsystem.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name ="identity_number", nullable = false, length = 11)
    private String identityNumber;

    @Column(name ="first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name ="last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    @Column(name ="birth_date", nullable = false, length = 50)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Application> application;

    /*
    @Column(name = "guarantee", nullable = true, precision=6, scale=0)
    private BigDecimal guarantee;
    */

}
