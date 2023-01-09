package com.rsqtechnologies.medicalservice.doctors.services;

import com.rsqtechnologies.medicalservice.doctors.exceptions.InvalidDoctorIdException;
import com.rsqtechnologies.medicalservice.doctors.repository.DoctorRepository;
import com.rsqtechnologies.medicalservice.doctors.repository.entity.Doctor;
import com.rsqtechnologies.medicalservice.doctors.services.dto.DoctorInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public void add(DoctorInfoDto doctorInfoDto) {
        doctorRepository.save(doctorInfoDto.toEntity());
    }

    @Override
    public List<DoctorInfoDto> getAll() {
        return doctorRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public DoctorInfoDto getBy(Long id) {
        return toDto(doctorRepository.findById(id).orElseThrow(InvalidDoctorIdException::new));
    }

    @Override
    public void update(Long id, DoctorInfoDto doctor) {
        DoctorInfoDto doctorInfoDto = getBy(id);
        doctorInfoDto.setName(doctor.getName());
        doctorInfoDto.setSpecialization(doctor.getSpecialization());
        doctorRepository.save(doctorInfoDto.toEntity());
    }

    @Override
    public void delete(Long id) {
        final DoctorInfoDto doctorInfoDto = getBy(id);
        doctorRepository.delete(doctorInfoDto.toEntity());
    }


    private DoctorInfoDto toDto(Doctor doctor) {
        return DoctorInfoDto
                .builder()
                .id(doctor.getId())
                .name(doctor.getName())
                .specialization(doctor.getSpecialization())
                .build();
    }
}
