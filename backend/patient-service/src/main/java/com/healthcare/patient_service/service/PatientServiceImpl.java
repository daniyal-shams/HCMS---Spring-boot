package com.healthcare.patient_service.service;

import com.healthcare.patient_service.dto.PatientDto;
import com.healthcare.patient_service.kafka.PatientEventProducer;
import com.healthcare.patient_service.exception.PatientNotFoundException;
import com.healthcare.patient_service.model.Patient;
import com.healthcare.patient_service.repository.PatientRepository;
import com.healthcare.patient_service.mapper.PatientMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private final PatientEventProducer producer;

    public PatientServiceImpl(PatientRepository patientRepository, PatientEventProducer producer) {
        this.patientRepository = patientRepository;
        this.producer = producer;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found", null, id));
        return PatientMapper.toDto(patient);
    }

    @Override
    @Transactional
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = PatientMapper.toEntity(patientDto);
        Patient savedPatient = patientRepository.save(patient);
        producer.sendPatientCreatedEvent(savedPatient.getId(), savedPatient.getFirstName(), savedPatient.getLastName(),savedPatient.getEmail()); // Corrected variable name
        return PatientMapper.toDto(savedPatient);
    }

    @Override
    @Transactional
    public PatientDto updatePatient(Long id, PatientDto patientDto) {
        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found", null, id));

        existingPatient.setFirstName(patientDto.getFirstName());
        existingPatient.setLastName(patientDto.getLastName());
        existingPatient.setEmail(patientDto.getEmail());
        existingPatient.setPhoneNumber(patientDto.getPhoneNumber());
        existingPatient.setAddress(patientDto.getAddress());
        existingPatient.setDateOfBirth(patientDto.getDateOfBirth());

        Patient updatedPatient = patientRepository.save(existingPatient);
        producer.sendPatientUpdatedEvent(updatedPatient.getId(), updatedPatient.getFirstName(), updatedPatient.getLastName(),updatedPatient.getEmail()); 
        return PatientMapper.toDto(updatedPatient);
    }

    @Override
    @Transactional
    public void deletePatient(Long id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient with ID " + id + " not found", null, id);
        }
        patientRepository.deleteById(id);
    }

    @Override
    public List<PatientDto> getPatients(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        Page<Patient> patientPage = patientRepository.findAll(pageRequest);
        return patientPage.stream()
                .map(PatientMapper::toDto)
                .collect(Collectors.toList());
    }

}