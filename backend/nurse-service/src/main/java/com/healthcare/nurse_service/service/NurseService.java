package com.healthcare.nurse_service.service;

import java.util.List;

import com.healthcare.nurse_service.dto.NurseDto;

public interface NurseService {
    NurseDto getNurseById(Long id);
    List<NurseDto> getAllNurses();
    NurseDto createNurse(NurseDto nurseDto);
    NurseDto updateNurse(Long id, NurseDto nurseDto);
    void deleteNurse(Long id);

    // Pagination and sorting
    List<NurseDto> getNursesByPage(int page, int size);
    List<NurseDto> getNursesByPageAndSort(int page, int size, String sortBy);
}