package com.rsqtechnologies.medicalservice.patients.controller.request;

import com.rsqtechnologies.medicalservice.patients.services.dto.PatientDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PatientRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Address address;

    public PatientDto toDto() {
        return PatientDto
                .builder()
                .firstName(firstName)
                .lastName(lastName)
                .addressDto(address.toDto())
                .build();
    }
}
