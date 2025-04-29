package com.healthcare.auth_service.service;

import com.healthcare.auth_service.client.UserServiceClient;
import com.healthcare.auth_service.dto.UserDto;
import com.healthcare.auth_service.utility.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final UserServiceClient userServiceClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(UserServiceClient userServiceClient, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userServiceClient = userServiceClient;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public String authenticateUser(String username, String password) {
        logger.info("Authenticating user: {}", username);
        UserDto user = userServiceClient.getUserByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            logger.error("Invalid username or password for user: {}", username);
            throw new RuntimeException("Invalid username or password");
        }
        return jwtUtil.generateToken(user);
    }

    @Override       
    public String registerUser(String username, String password, String email) {
        logger.info("Registering user: {}", username);
        UserDto existingUser = userServiceClient.getUserByUsername(username);
        if (existingUser != null) {
            logger.error("Username already exists: {}", username);
            throw new RuntimeException("Username already exists");
        }
        UserDto newUser = new UserDto();
        newUser.setName(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        userServiceClient.createUser(newUser);
        return jwtUtil.generateToken(newUser);
    }

}