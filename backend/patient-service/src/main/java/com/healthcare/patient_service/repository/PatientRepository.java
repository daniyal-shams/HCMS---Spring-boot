package com.healthcare.patient_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.patient_service.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}