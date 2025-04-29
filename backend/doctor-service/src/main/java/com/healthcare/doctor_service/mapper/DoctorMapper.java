package com.healthcare.doctor_service.mapper;

import com.healthcare.doctor_service.dto.DoctorDto;
import com.healthcare.doctor_service.model.Doctor;

public final class DoctorMapper {

    // Private constructor to prevent instantiation
    private DoctorMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static DoctorDto toDto(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setId(doctor.getId());
        doctorDto.setName(doctor.getName());
        doctorDto.setSpecialization(doctor.getSpecialization());
        doctorDto.setContactNumber(doctor.getContactNumber());
        doctorDto.setEmail(doctor.getEmail());
        return doctorDto;
    }

    public static Doctor toEntity(DoctorDto doctorDto) {
        if (doctorDto == null) {
            return null;
        }
        Doctor doctor = new Doctor();
        doctor.setId(doctorDto.getId());
        doctor.setName(doctorDto.getName());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setContactNumber(doctorDto.getContactNumber());
        doctor.setEmail(doctorDto.getEmail());
        return doctor;
    }
}
