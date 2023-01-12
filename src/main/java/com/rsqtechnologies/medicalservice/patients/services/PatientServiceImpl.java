package com.rsqtechnologies.medicalservice.patients.services;

import com.rsqtechnologies.medicalservice.patients.exceptions.InvalidPatientIdException;
import com.rsqtechnologies.medicalservice.patients.repository.PatientRepository;
import com.rsqtechnologies.medicalservice.patients.repository.entity.Address;
import com.rsqtechnologies.medicalservice.patients.repository.entity.Patient;
import com.rsqtechnologies.medicalservice.patients.services.dto.AddressDto;
import com.rsqtechnologies.medicalservice.patients.services.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public void add(PatientDto patientDto) {
        patientRepository.save(patientDto.toEntity());
    }

    @Override
    public List<PatientDto> getAll() {
        return patientRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public PatientDto getBy(Long id) {
        return toDto(patientRepository.findById(id).orElseThrow(InvalidPatientIdException::new));
    }

    @Override
    public void update(Long id, PatientDto patient) {
        PatientDto patientDto = getBy(id);
        patientDto.setFirstName(patient.getFirstName());
        patientDto.setLastName(patient.getLastName());
        final AddressDto updatedAddress = updateAddress(patient, patientDto);
        patientDto.setAddressDto(updatedAddress);
        patientRepository.save(patientDto.toEntity());
    }

    private AddressDto updateAddress(PatientDto patient, PatientDto patientDto) {
        AddressDto addressDto = patientDto.getAddressDto();
        addressDto.setStreet(patient.getAddressDto().getStreet());
        addressDto.setPostCode(patient.getAddressDto().getPostCode());
        addressDto.setCity(patient.getAddressDto().getCity());
        patientDto.setAddressDto(patient.getAddressDto());
        return addressDto;
    }

    @Override
    public void delete(Long id) {
        final PatientDto patientDto = getBy(id);
        patientRepository.delete(patientDto.toEntity());
    }


    private PatientDto toDto(Patient patient) {
        return PatientDto
                .builder()
                .id(patient.getId())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .addressDto(toDto(patient.getAddress()))
                .build();
    }

    private AddressDto toDto(Address address) {
        return AddressDto
                .builder()
                .id(address.getId())
                .street(address.getStreet())
                .postCode(address.getPostCode())
                .city(address.getCity())
                .build();
    }
}
