package com.healthcare.appointment_service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.healthcare.appointment_service.dto.AppointmentDto;
import com.healthcare.appointment_service.model.Appointment;

import jakarta.persistence.MappedSuperclass;

public class AppointmentMapper {

    public static Appointment toEntity(AppointmentDto appointmentDto) {
        if (appointmentDto == null) {
            return null;
        }
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDto.getId());
        appointment.setPatientId(appointmentDto.getPatientId());
        appointment.setDoctorId(appointmentDto.getDoctorId());
        appointment.setAppointmentDate(appointmentDto.getAppointmentDate());
        appointment.setAppointmentTime(appointmentDto.getAppointmentTime());
        appointment.setStatus(appointmentDto.getStatus());
        return appointment;
    }
    public static AppointmentDto toDto(Appointment appointment) {
        if (appointment == null) {
            return null;
        }
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setPatientId(appointment.getPatientId());
        appointmentDto.setDoctorId(appointment.getDoctorId());
        appointmentDto.setAppointmentDate(appointment.getAppointmentDate());
        appointmentDto.setAppointmentTime(appointment.getAppointmentTime());
        appointmentDto.setStatus(appointment.getStatus());
        return appointmentDto;
    }
    public static List<AppointmentDto> toDtoList(List<Appointment> appointments) {
        if (appointments == null) {
            return null;
        }
        return appointments.stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }
    public static List<Appointment> toEntityList(List<AppointmentDto> appointmentDtos) {
        if (appointmentDtos == null) {
            return null;
        }
        return appointmentDtos.stream()
                .map(AppointmentMapper::toEntity)
                .collect(Collectors.toList());
    }

}