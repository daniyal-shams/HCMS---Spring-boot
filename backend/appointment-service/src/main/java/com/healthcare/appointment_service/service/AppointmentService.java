package com.healthcare.appointment_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.healthcare.appointment_service.dto.AppointmentDto;

@Service
public interface AppointmentService {

    AppointmentDto createAppointment(AppointmentDto appointmentDto);

    AppointmentDto getAppointmentById(Long id);

    AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto);

    void deleteAppointment(Long id);

    List<AppointmentDto> getAllAppointments();

    List<AppointmentDto> getAppointmentsByPatientId(Long patientId);

    List<AppointmentDto> getAppointmentsByDoctorId(Long doctorId);
    // pagination and sorting methods can be added here
    List<AppointmentDto> getAppointments(int page, int size, String sortBy);


}
