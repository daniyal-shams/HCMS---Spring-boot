package com.healthcare.patient_service.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthcare.patient_service.dto.PatientDto;
import com.healthcare.patient_service.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
@Validated
@CrossOrigin(origins="http://localhost:8080")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientDto> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("{id}")
    public PatientDto getPatientById(@PathVariable("id") Long id) {
        return patientService.getPatientById(id);
    }

    @PostMapping
    public PatientDto createPatient(@Valid @RequestBody PatientDto patientDto) {
        return patientService.createPatient(patientDto);
    }

    @PutMapping("{id}")
    public PatientDto updatePatient(@PathVariable Long id, @Valid @RequestBody PatientDto patientDto) {
        return patientService.updatePatient(id, patientDto);
    }

    @DeleteMapping("{id}")
    public void deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
    }

}
