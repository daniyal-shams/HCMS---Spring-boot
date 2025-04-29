package com.healthcare.doctor_service.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.healthcare.doctor_service.dto.NurseDto;

@FeignClient(name = "nurse-service")
public interface NurseClient {
    @GetMapping("/nurses")
    List<NurseDto> getAllNurses();
}


