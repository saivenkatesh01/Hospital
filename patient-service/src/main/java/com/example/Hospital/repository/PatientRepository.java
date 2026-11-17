package com.example.Hospital.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Hospital.model.Patient;

import jakarta.persistence.LockModeType;

@Repository 
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT p FROM Patient p WHERE p.patientId = :id")
    Optional<Patient>findByIdForUpdate(@Param("id") int patientId);

}
