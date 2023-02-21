package com.keremcengiz0.creditapplicationsystem.repositories;

import com.keremcengiz0.creditapplicationsystem.entities.ApplicationError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationErrorRepository extends JpaRepository<ApplicationError, Long> {
}
