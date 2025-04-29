package com.healthcare.nurse_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.healthcare.nurse_service.model.Nurse;

@Repository
public interface NurseRepository extends JpaRepository<Nurse , Long> {

}
