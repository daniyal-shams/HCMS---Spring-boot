package com.healthcare.patient_service.service;

import java.util.List;

import com.healthcare.patient_service.dto.PatientDto;

public interface  PatientService {

    List<PatientDto> getAllPatients();
    PatientDto getPatientById(Long id);
    PatientDto createPatient(PatientDto patientDto);
    PatientDto updatePatient(Long id, PatientDto patientDto);
    void deletePatient(Long id);

    List<PatientDto> getPatients(int page, int size, String sortBy, String sortDirection);

}
