package com.rsqtechnologies.medicalservice.doctors.controller.request;

import com.rsqtechnologies.medicalservice.doctors.repository.entity.Specialization;
import com.rsqtechnologies.medicalservice.doctors.services.dto.DoctorInfoDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DoctorRequest {

    @NotBlank
    private String name;

    @NotBlank
    private Specialization specialization;

    public DoctorInfoDto toDto() {
        return DoctorInfoDto.builder().name(name).specialization(specialization).build();
    }
}
