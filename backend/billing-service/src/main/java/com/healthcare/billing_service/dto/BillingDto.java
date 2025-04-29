package com.healthcare.billing_service.dto;


public class BillingDto {

    private Long id;
    private Long patientId;
    private Long appointmentId;
    private Double amount;
    private String status; 
    private String billingDate;

    public BillingDto() {
    }

    public BillingDto(Long id, Long patientId, Long appointmentId, Double amount, String status, String billingDate) {
        this.id = id;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(String billingDate) {
        this.billingDate = billingDate;
    }
    @Override
    public String toString() {
        return "BillingDto{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", appointmentId=" + appointmentId +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", billingDate='" + billingDate + '\'' +
                '}';
    }

}
