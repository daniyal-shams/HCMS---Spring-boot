package com.healthcare.report_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthcare.report_service.dto.AppointmentDto;

@FeignClient(name = "appointment-service")
public interface AppointmentServiceClient {

    @GetMapping("/api/appointments")
    Page<AppointmentDto> getAllAppointments(@RequestParam("page") int page, @RequestParam("size") int size);
}

