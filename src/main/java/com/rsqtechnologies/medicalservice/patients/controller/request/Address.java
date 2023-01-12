package com.rsqtechnologies.medicalservice.patients.controller.request;

import com.rsqtechnologies.medicalservice.patients.services.dto.AddressDto;
import lombok.Data;

@Data
public class Address {

    private String street;

    private String postCode;

    private String city;

    public AddressDto toDto() {
        return AddressDto
                .builder()
                .street(street)
                .postCode(postCode)
                .city(city)
                .build();
    }
}
