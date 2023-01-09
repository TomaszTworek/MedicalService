package com.rsqtechnologies.medicalservice.doctors.exceptions;

public class InvalidDoctorIdException extends RuntimeException {

    public static final String NO_DOCTOR_WITH_GIVEN_ID = "No doctor with given id was found";

    public InvalidDoctorIdException() {
        super(NO_DOCTOR_WITH_GIVEN_ID);
    }
}
