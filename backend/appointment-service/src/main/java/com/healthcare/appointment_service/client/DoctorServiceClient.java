package com.healthcare.appointment_service.client;

import org.springframework.data.domain.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthcare.appointment_service.dto.DoctorDto;

@FeignClient(name = "doctor-service")
public interface DoctorServiceClient {
    @GetMapping("/doctors/{id}")
    DoctorDto getDoctorById(@PathVariable("id") Long id);

    @GetMapping("/doctors")
    Page<DoctorDto> getAllDoctors(@RequestParam("page") int page, @RequestParam("size") int size);
}


