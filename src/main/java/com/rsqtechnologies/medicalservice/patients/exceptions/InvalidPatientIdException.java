package com.rsqtechnologies.medicalservice.patients.exceptions;

public class InvalidPatientIdException extends RuntimeException {

    public static final String NO_PATIENT_WITH_GIVEN_ID = "No patient with given id was found";

    public InvalidPatientIdException() {
        super(NO_PATIENT_WITH_GIVEN_ID);
    }
}
