package com.rsqtechnologies.medicalservice.doctors.repository;

import com.rsqtechnologies.medicalservice.doctors.repository.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
