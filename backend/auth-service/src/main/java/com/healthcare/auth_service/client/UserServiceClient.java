package com.healthcare.auth_service.client;

import com.healthcare.auth_service.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/api/users/search")
    UserDto getUserByUsername(@RequestParam("username") String username);
    void createUser(UserDto userDto);
}