package com.healthcare.doctor_service.dto;

public class NotificationDto {
    private String recipient;
    private String subject;
    private String message;


    public NotificationDto() {
    }
    public NotificationDto(String recipient, String subject, String message ) {
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
 
    }
    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "NotificationDto{" +
                "recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}