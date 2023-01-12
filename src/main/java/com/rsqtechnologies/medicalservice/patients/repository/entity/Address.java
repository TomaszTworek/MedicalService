package com.rsqtechnologies.medicalservice.patients.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String postCode;

    private String city;

    @OneToOne(mappedBy = "address")
    private Patient patient;

    public Address(Long id, String street, String postCode, String city) {
        this.id = id;
        this.street = street;
        this.postCode = postCode;
        this.city = city;
    }
}
