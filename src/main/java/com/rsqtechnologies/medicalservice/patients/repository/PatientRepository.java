package com.rsqtechnologies.medicalservice.patients.repository;

import com.rsqtechnologies.medicalservice.patients.repository.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
