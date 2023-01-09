package com.rsqtechnologies.medicalservice.doctors.configuration;

import com.rsqtechnologies.medicalservice.doctors.controller.request.SpecializationEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DoctorConfiguration implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new SpecializationEnumConverter());
    }
}
