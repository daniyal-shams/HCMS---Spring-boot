package com.healthcare.appointment_service.client;

import org.springframework.data.domain.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthcare.appointment_service.dto.PatientDto;

@FeignClient(name = "patient-service")
public interface PatientServiceClient {
    @GetMapping("/patients/{id}")
    PatientDto getPatientById(@PathVariable("id") Long id);

    @GetMapping("/patients")
    Page<PatientDto> getAllPatients(@RequestParam("page") int page, @RequestParam("size") int size);

}
