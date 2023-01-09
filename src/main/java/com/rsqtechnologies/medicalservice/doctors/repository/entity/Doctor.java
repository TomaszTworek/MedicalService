package com.rsqtechnologies.medicalservice.doctors.repository.entity;

import com.rsqtechnologies.medicalservice.doctors.services.dto.DoctorInfoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

}
