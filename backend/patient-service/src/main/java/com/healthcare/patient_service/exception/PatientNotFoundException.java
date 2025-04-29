package com.healthcare.patient_service.exception;

public class PatientNotFoundException extends RuntimeException {
    public PatientNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
