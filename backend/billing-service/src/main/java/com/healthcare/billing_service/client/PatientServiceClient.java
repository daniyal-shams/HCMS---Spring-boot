package com.healthcare.notification_service.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.healthcare.notification_service.dto.PatientDto;

public interface PatientServiceClient {

    @GetMapping("/api/patients/{id}")
    PatientDto getPatientById(@PathVariable("id") Long id);

}
