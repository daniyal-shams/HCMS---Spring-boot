package com.healthcare.nurse_service.service;

import com.healthcare.nurse_service.dto.NurseDto;
import com.healthcare.nurse_service.exception.NurseNotFoundException;
import com.healthcare.nurse_service.mapper.NurseMapper;
import com.healthcare.nurse_service.model.Nurse;
import com.healthcare.nurse_service.repository.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NurseServiceImpl implements NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    @Override
    public NurseDto getNurseById(Long id) {
        Nurse nurse = nurseRepository.findById(id)
                .orElseThrow(() -> new NurseNotFoundException("Nurse not found with id: " + id));
        return NurseMapper.toDto(nurse);
    }

    @Override
    public List<NurseDto> getAllNurses() {
        return nurseRepository.findAll().stream()
                .map(NurseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public NurseDto createNurse(NurseDto nurseDto) {
        Nurse nurse = NurseMapper.toEntity(nurseDto);
        Nurse savedNurse = nurseRepository.save(nurse);
        return NurseMapper.toDto(savedNurse);
    }

    @Override
    public NurseDto updateNurse(Long id, NurseDto nurseDto) {
        Nurse existingNurse = nurseRepository.findById(id)
                .orElseThrow(() -> new NurseNotFoundException("Nurse not found with id: " + id));
        existingNurse.setName(nurseDto.getName());
        existingNurse.setPhoneNumber(nurseDto.getPhoneNumber());
        existingNurse.setSpecialization(nurseDto.getSpecialization());
        existingNurse.setDepartment(nurseDto.getDepartment());
        Nurse updatedNurse = nurseRepository.save(existingNurse);
        return NurseMapper.toDto(updatedNurse);
    }

    @Override
    public void deleteNurse(Long id) {
        Nurse nurse = nurseRepository.findById(id)
                .orElseThrow(() -> new NurseNotFoundException("Nurse not found with id: " + id));
        nurseRepository.delete(nurse);
    }

    @Override
    public List<NurseDto> getNursesByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return nurseRepository.findAll(pageable).stream()
                .map(NurseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NurseDto> getNursesByPageAndSort(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return nurseRepository.findAll(pageable).stream()
                .map(NurseMapper::toDto)
                .collect(Collectors.toList());
    }
}