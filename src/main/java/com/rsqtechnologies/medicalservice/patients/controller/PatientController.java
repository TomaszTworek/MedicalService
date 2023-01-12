package com.rsqtechnologies.medicalservice.patients.controller;

import com.rsqtechnologies.medicalservice.patients.controller.request.PatientRequest;
import com.rsqtechnologies.medicalservice.patients.services.PatientService;
import com.rsqtechnologies.medicalservice.patients.services.dto.PatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDto>> getAll() {
        final List<PatientDto> patientDtos = patientService.getAll();
        return new ResponseEntity<>(patientDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> get(@PathVariable("id") Long doctorId) {
        final PatientDto patientDto = patientService.getBy(doctorId);
        return new ResponseEntity<>(patientDto, HttpStatus.OK);
    }

    @PostMapping
    public void add(@RequestBody PatientRequest patientRequest) {
        patientService.add(patientRequest.toDto());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long patientId, @RequestBody PatientRequest patientRequest) {
        patientService.update(patientId, patientRequest.toDto());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long patientId) {
        patientService.delete(patientId);
    }
}
