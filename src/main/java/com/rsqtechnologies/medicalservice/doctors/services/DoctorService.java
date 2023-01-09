package com.rsqtechnologies.medicalservice.doctors.services;

import com.rsqtechnologies.medicalservice.doctors.services.dto.DoctorInfoDto;

import java.util.List;

public interface DoctorService {

    void add(DoctorInfoDto doctorInfoDto);

    List<DoctorInfoDto> getAll();

    DoctorInfoDto getBy(Long id);

    void update(Long id, DoctorInfoDto doctorInfoDto);

    void delete(Long id);

}
