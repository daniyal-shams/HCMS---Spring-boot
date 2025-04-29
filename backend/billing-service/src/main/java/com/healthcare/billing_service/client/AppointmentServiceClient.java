package com.healthcare.notification_service.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.healthcare.notification_service.dto.AppointmentDto;

public interface AppointmentServiceClient {

    @GetMapping("/api/appointments/{id}")
    AppointmentDto getAppointmentById(@PathVariable("id") Long id);

}
