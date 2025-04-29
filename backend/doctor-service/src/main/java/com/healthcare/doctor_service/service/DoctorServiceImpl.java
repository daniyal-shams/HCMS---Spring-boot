package com.healthcare.doctor_service.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.doctor_service.dto.DoctorDto;
import com.healthcare.doctor_service.exception.DoctorNotFoundException;
import com.healthcare.doctor_service.kafka.DoctorEventProducer;
import com.healthcare.doctor_service.mapper.DoctorMapper;
import com.healthcare.doctor_service.model.Doctor;
import com.healthcare.doctor_service.repository.DoctorRepository;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    private final DoctorEventProducer producer;

    public DoctorServiceImpl(DoctorRepository doctorRepository, DoctorEventProducer producer) {
        this.doctorRepository = doctorRepository;
        this.producer = producer;
    }

    @Override
    @Transactional
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        Doctor doctor = DoctorMapper.toEntity(doctorDto);
        doctor.setId(null);
        Doctor savedDoctor = doctorRepository.save(doctor);
        producer.sendDoctorCreatedEvent(savedDoctor.getId(), savedDoctor.getName(), savedDoctor.getEmail()); // Corrected variable name
        return DoctorMapper.toDto(savedDoctor);
    }

    @Override
    @Transactional(readOnly = true)
    public DoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));
        return DoctorMapper.toDto(doctor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(DoctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DoctorDto updateDoctor(Long id, DoctorDto doctorDto) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor with ID " + id + " not found"));

        existingDoctor.setName(doctorDto.getName());
        existingDoctor.setSpecialization(doctorDto.getSpecialization());
        existingDoctor.setContactNumber(doctorDto.getContactNumber());
        existingDoctor.setEmail(doctorDto.getEmail());

        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        producer.sendDoctorUpdatedEvent(updatedDoctor.getId(), updatedDoctor.getName(), updatedDoctor.getEmail()); // Corrected variable name
        return DoctorMapper.toDto(updatedDoctor);
    }

    @Override
    @Transactional
    public void deleteDoctor(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new DoctorNotFoundException("Doctor with ID " + id + " not found");
        }
        doctorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DoctorDto> getDoctorsWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
        );
        return doctorRepository.findAll(pageable)
                .map(DoctorMapper::toDto);
    }
}