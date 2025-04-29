package com.healthcare.doctor_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.healthcare.doctor_service.dto.UserDto;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/api/users/search")
    UserDto getUserByUsername(@RequestParam("username") String username);
}