package com.keremcengiz0.creditapplicationsystem.repositories;

import com.keremcengiz0.creditapplicationsystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByIdentityNumber(String identityNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Customer> findCustomerByIdentityNumber(String identityNumber);
}
