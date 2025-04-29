package com.healthcare.patient_service.mapper;

import com.healthcare.patient_service.dto.PatientDto;
import com.healthcare.patient_service.model.Patient;

public class PatientMapper {

    public static PatientDto toDto(Patient patient) {
        if (patient == null) {
            return null;
        }
        PatientDto dto = new PatientDto();
        dto.setId(patient.getId());
        dto.setFirstName(patient.getFirstName());
        dto.setLastName(patient.getLastName());
        dto.setEmail(patient.getEmail());
        dto.setPhoneNumber(patient.getPhoneNumber());
        dto.setAddress(patient.getAddress());
        dto.setDateOfBirth(patient.getDateOfBirth());
        return dto;
    }

    public static Patient toEntity(PatientDto patientDto) {
        if (patientDto == null) {
            return null;
        }
        Patient patient = new Patient();
        patient.setId(patientDto.getId());
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());
        patient.setEmail(patientDto.getEmail());
        patient.setPhoneNumber(patientDto.getPhoneNumber());
        patient.setAddress(patientDto.getAddress());
        patient.setDateOfBirth(patientDto.getDateOfBirth());
        return patient;
    }

}
