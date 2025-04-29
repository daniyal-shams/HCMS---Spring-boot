package com.healthcare.doctor_service.exception;


public class DoctorNotFoundException extends  RuntimeException {
    private static final long serialVersionUID = 1L;

    public DoctorNotFoundException(String message) {
        super(message);
    }

    public DoctorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public DoctorNotFoundException(Throwable cause) {
        super(cause);
    }
    public DoctorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
