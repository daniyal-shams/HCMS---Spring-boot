package com.healthcare.appointment_service.service;

import com.healthcare.appointment_service.client.DoctorServiceClient;
import com.healthcare.appointment_service.client.PatientServiceClient;
import com.healthcare.appointment_service.dto.AppointmentDto;
import com.healthcare.appointment_service.dto.DoctorDto;
import com.healthcare.appointment_service.dto.PatientDto;
import com.healthcare.appointment_service.exception.AppointmentNotFoundException;
import com.healthcare.appointment_service.mapper.AppointmentMapper;
import com.healthcare.appointment_service.model.Appointment;
import com.healthcare.appointment_service.repository.AppointmentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientServiceClient patientServiceClient;
    private final DoctorServiceClient doctorServiceClient;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  PatientServiceClient patientServiceClient,
                                  DoctorServiceClient doctorServiceClient) {
        this.appointmentRepository = appointmentRepository;
        this.patientServiceClient = patientServiceClient;
        this.doctorServiceClient = doctorServiceClient;
    }

    @Override
    @Transactional
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        PatientDto patient = patientServiceClient.getPatientById(appointmentDto.getPatientId());
        DoctorDto doctor = doctorServiceClient.getDoctorById(appointmentDto.getDoctorId());

        Appointment appointment = AppointmentMapper.toEntity(appointmentDto);
        appointment.setPatientName(patient.getFirstName() + " " + patient.getLastName());
        appointment.setDoctorName(doctor.getName());

        Appointment savedAppointment = appointmentRepository.save(appointment);
        return AppointmentMapper.toDto(savedAppointment);
    }

    @Override
    @Transactional(readOnly = true)
    public AppointmentDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + id));
        return AppointmentMapper.toDto(appointment);
    }

    @Override
    @Transactional
    public AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDto) {
        if (!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException("Appointment not found with id: " + id);
        }

        Appointment appointment = AppointmentMapper.toEntity(appointmentDto);
        appointment.setId(id);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return AppointmentMapper.toDto(updatedAppointment);
    }

    @Override
    @Transactional
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new AppointmentNotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDto> getAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDto> getAppointments(int page, int size, String sortBy) {
        Page<Appointment> appointmentsPage = appointmentRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
        return appointmentsPage.getContent().stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDto> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return appointments.stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AppointmentDto> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        return appointments.stream()
                .map(AppointmentMapper::toDto)
                .collect(Collectors.toList());
    }
}