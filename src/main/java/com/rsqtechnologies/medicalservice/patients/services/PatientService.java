package com.rsqtechnologies.medicalservice.patients.services;

import com.rsqtechnologies.medicalservice.patients.services.dto.PatientDto;

import java.util.List;

public interface PatientService {

    void add(PatientDto patientDto);

    List<PatientDto> getAll();

    PatientDto getBy(Long id);

    void update(Long id, PatientDto patientDto);

    void delete(Long id);

}
