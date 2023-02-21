package com.keremcengiz0.creditapplicationsystem.repositories;

import com.keremcengiz0.creditapplicationsystem.entities.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCustomerIdentityNumber(String identityNumber);

    @Query(value = "Select a.* from applications as a "
            + "inner join customers as c on a.customer_id = c.id "
            + "where c.identity_number =:identityNumber "
            + "and c.birth_date =:birthDate", nativeQuery = true)
    List<Application> getAllApplicationsByCustomerIdentityNumberAndBirthdate(String identityNumber, LocalDate birthDate);
}
