package com.healthcare.billing_service.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;
    private Long appointmentId;
    private BigDecimal amount;
    private String status; 
    private LocalDateTime billingDate;

    public Billing() {
    }
    public Billing(Long patientId, Long appointmentId, BigDecimal amount, String status, LocalDateTime billingDate) {
        this.patientId = patientId;
        this.appointmentId = appointmentId;
        this.amount = amount;
        this.status = status;
        this.billingDate = billingDate;
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
    public Long getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getBillingDate() {
        return billingDate;
    }
    public void setBillingDate(LocalDateTime billingDate) {
        this.billingDate = billingDate;
    }
    @Override
    public String toString() {
        return "Billing{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", appointmentId=" + appointmentId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", billingDate=" + billingDate +
                '}';
    }

}