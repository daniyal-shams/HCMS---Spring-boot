package com.healthcare.appointment_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class AppointmentDto {
    // validation annotations can be added here
        @Schema(description = "Auto-generated appointment ID", example = "1")

    private Long id;
    @NotBlank(message = "Patient name is required")
    private Long patientId;
    @NotBlank(message = "Doctor name is required")
    private Long doctorId;
    @NotBlank(message = "Appointment date is required")
    @Schema(description = "Appointment date", example = "2023-10-01")
    private String appointmentDate;
    @NotBlank(message = "Appointment time is required")
    @Schema(description = "Appointment time", example = "10:00 AM")
    private String appointmentTime;
    @NotBlank(message = "Status is required")
    @Schema(description = "Appointment status", example = "Scheduled")
    private String status;



    public AppointmentDto() {
    }

    public AppointmentDto(Long id, Long patientId, Long doctorId, String appointmentDate, String appointmentTime, String status ) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
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
