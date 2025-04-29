package com.healthcare.nurse_service.mapper;

import com.healthcare.nurse_service.dto.NurseDto;
import com.healthcare.nurse_service.model.Nurse;

public class NurseMapper {

    public static NurseDto toDto(Nurse nurse) {
        if (nurse == null) {
            return null;
        }
        return new NurseDto(
            nurse.getId(),
            nurse.getName(),
            nurse.getPhoneNumber(),
            nurse.getSpecialization(),
            nurse.getDepartment()
        );
    }

    public static Nurse toEntity(NurseDto nurseDto) {
        if (nurseDto == null) {
            return null;
        }
        return new Nurse(
            nurseDto.getId(),
            nurseDto.getName(),
            nurseDto.getPhoneNumber(),
            nurseDto.getSpecialization(),
            nurseDto.getDepartment()
        );
    }
}