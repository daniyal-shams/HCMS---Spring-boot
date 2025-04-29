package com.healthcare.doctor_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.doctor_service.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {


}
