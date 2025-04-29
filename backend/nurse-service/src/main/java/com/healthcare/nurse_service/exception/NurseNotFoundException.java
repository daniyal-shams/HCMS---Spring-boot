package com.healthcare.nurse_service.exception;

public class NurseNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public NurseNotFoundException(String message) {
        super(message);
    }  
    public NurseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public NurseNotFoundException(Throwable cause) {
        super(cause);
    }
    public NurseNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }



}
