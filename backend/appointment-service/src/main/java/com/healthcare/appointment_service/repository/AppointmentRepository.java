package com.healthcare.appointment_service.repository;

import org.springframework.stereotype.Repository;

import com.healthcare.appointment_service.model.Appointment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;    

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorId(Long doctorId);

}
