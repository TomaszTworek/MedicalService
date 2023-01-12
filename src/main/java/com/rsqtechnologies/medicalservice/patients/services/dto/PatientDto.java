package com.rsqtechnologies.medicalservice.patients.services.dto;

import com.rsqtechnologies.medicalservice.patients.repository.entity.Patient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientDto {

    private Long id;
    private String firstName;
    private String lastName;
    private AddressDto addressDto;


    public Patient toEntity() {
        return new Patient(id, firstName, lastName, addressDto.toEntity());
    }
}
