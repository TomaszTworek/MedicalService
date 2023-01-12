package com.rsqtechnologies.medicalservice.doctors.controller.request;

import com.rsqtechnologies.medicalservice.doctors.repository.entity.Specialization;
import org.springframework.core.convert.converter.Converter;

public class SpecializationEnumConverter implements Converter<String, Specialization> {

    @Override
    public Specialization convert(String source) {
        return Specialization.valueOf(source.toUpperCase());
    }
}
