package com.rsqtechnologies.medicalservice.patients.services.dto;

import com.rsqtechnologies.medicalservice.patients.repository.entity.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

    private Long id;

    private String street;

    private String postCode;

    private String city;

    public Address toEntity() {
        return new Address(id, street, postCode, city);
    }
}
