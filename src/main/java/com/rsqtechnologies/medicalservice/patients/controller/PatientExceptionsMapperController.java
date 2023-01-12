package com.rsqtechnologies.medicalservice.patients.controller;

import com.rsqtechnologies.medicalservice.patients.exceptions.InvalidPatientIdException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PatientExceptionsMapperController {

    @ExceptionHandler(InvalidPatientIdException.class)
    public ResponseEntity<Error> handleInvalidPatientIdException(InvalidPatientIdException ex) {
        return new ResponseEntity<>(new Error(ex.getMessage(), HttpStatus.BAD_REQUEST.toString()),
                HttpStatus.BAD_REQUEST);
    }

    @Data
    public static class Error {
        private String errorMessage;

        private String httpStatus;

        public Error(String errorMessage, String httpStatus) {
            this.errorMessage = errorMessage;
            this.httpStatus = httpStatus;
        }
    }

}
