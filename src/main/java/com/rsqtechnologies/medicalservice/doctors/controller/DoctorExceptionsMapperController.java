package com.rsqtechnologies.medicalservice.doctors.controller;

import com.rsqtechnologies.medicalservice.doctors.exceptions.InvalidDoctorIdException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DoctorExceptionsMapperController {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleWrongSpecialization(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>(new Error(ex.getMessage(), HttpStatus.BAD_REQUEST.toString()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDoctorIdException.class)
    public ResponseEntity<Error> handleInvalidDoctorIdException(InvalidDoctorIdException ex) {
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
