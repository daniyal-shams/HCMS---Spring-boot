package com.healthcare.doctor_service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.healthcare.doctor_service.dto.DoctorDto;

@Service
public interface DoctorService {

    DoctorDto createDoctor(DoctorDto doctorDto);

    DoctorDto getDoctorById(Long id);

    List<DoctorDto> getAllDoctors();

    DoctorDto updateDoctor(Long id, DoctorDto doctorDto);

    void deleteDoctor(Long id);

    Page<DoctorDto> getDoctorsWithPaginationAndSorting(int page, int size, String sortBy, String sortDir);
}