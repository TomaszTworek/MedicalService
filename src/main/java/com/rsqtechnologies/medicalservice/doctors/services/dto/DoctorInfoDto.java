package com.rsqtechnologies.medicalservice.doctors.services.dto;

import com.rsqtechnologies.medicalservice.doctors.repository.entity.Doctor;
import com.rsqtechnologies.medicalservice.doctors.repository.entity.Specialization;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorInfoDto {

    private Long id;

    private String name;

    private Specialization specialization;

    public Doctor toEntity() {
        return new Doctor(id, name, specialization);
    }
}
