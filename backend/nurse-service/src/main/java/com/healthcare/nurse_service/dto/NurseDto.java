package com.healthcare.nurse_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;


public class NurseDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Nurse ID (auto-generated)")
    private Long id;
    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces")
    @Schema(example = "allen")
    private String name;
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number must be between 10 and 15 digits")
    @Schema(example = "+91892387423942")
    private String phoneNumber;
    @NotBlank(message = "Specialization is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Specialization must contain only letters and spaces")
    @Schema(example = "Cardiac Nurse")
    private String specialization;
    @NotBlank(message = "department is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "department must contain only letters and spaces")
    @Schema(example = "Cardiology")
    private String department;

    public NurseDto() {
    }

    public NurseDto(Long id, String name, String phoneNumber, String specialization, String department) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.department = department;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }           
    public void setName(String name) {
        this.name = name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    @Override
    public String toString() {
        return "NurseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", specialization='" + specialization + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

}
