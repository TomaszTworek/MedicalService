package com.rsqtechnologies.medicalservice.doctors.controller;

import com.rsqtechnologies.medicalservice.doctors.controller.request.DoctorRequest;
import com.rsqtechnologies.medicalservice.doctors.services.DoctorService;
import com.rsqtechnologies.medicalservice.doctors.services.dto.DoctorInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorInfoDto>> getAll() {
        final List<DoctorInfoDto> doctorInfoDtos = doctorService.getAll();
        return new ResponseEntity<>(doctorInfoDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorInfoDto> get(@PathVariable("docId") Long doctorId) {
        final DoctorInfoDto doctorInfoDto = doctorService.getBy(doctorId);
        return new ResponseEntity<>(doctorInfoDto, HttpStatus.OK);
    }

    @PostMapping
    public void add(@RequestBody DoctorRequest doctorRequest) {
        doctorService.add(doctorRequest.toDto());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("docId") Long doctorId, DoctorRequest doctorRequest) {
        doctorService.update(doctorId, doctorRequest.toDto());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("docId") Long doctorId) {
        doctorService.delete(doctorId);
    }
}
