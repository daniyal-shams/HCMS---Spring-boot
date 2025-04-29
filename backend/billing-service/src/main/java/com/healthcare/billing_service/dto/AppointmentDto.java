package com.healthcare.billing_service.dto;

import jakarta.validation.constraints.NotBlank;

public class AppointmentDto {
    // validation annotations can be added here

    private Long id;
    @NotBlank(message = "Patient name is required")
    private String patientName;
    @NotBlank(message = "Doctor name is required")
    private String doctorName;
    @NotBlank(message = "Appointment date is required")
    private String appointmentDate;
    @NotBlank(message = "Appointment time is required")
    private String appointmentTime;
    @NotBlank(message = "Status is required")
    private String status;

    public AppointmentDto() {
    }

    public AppointmentDto(Long id, String patientName, String doctorName, String appointmentDate, String appointmentTime, String status) {
        this.id = id;
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }   

}
