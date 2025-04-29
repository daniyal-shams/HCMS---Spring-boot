package com.healthcare.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.healthcare.auth_service.client.UserServiceClient;
import com.healthcare.auth_service.utility.JwtUtil;

@Service
public interface AuthService {


    public String authenticateUser(String username, String password) ;
    public String registerUser(String username, String password, String email) ;


}
