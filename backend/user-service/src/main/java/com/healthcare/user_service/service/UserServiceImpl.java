package com.healthcare.user_service.service.impl;

import com.healthcare.user_service.dto.UserDto;
import com.healthcare.user_service.exception.UserNotFoundException;
import com.healthcare.user_service.service.UserService;

import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final List<UserDto> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    private final Validator validator;

    public UserServiceImpl() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        validateUser(userDto);
        userDto.setId(idGenerator.getAndIncrement());
        users.add(userDto);
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        return findUserById(id);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        validateUser(userDto);
        UserDto existingUser = findUserById(id);
        existingUser.setName(userDto.getName());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());
        existingUser.setRoles(userDto.getRoles());
        return existingUser;
    }

    @Override
    public void deleteUser(Long id) {
        UserDto user = findUserById(id);
        users.remove(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public List<UserDto> getUsersByRole(String role) {
        return users.stream()
                .filter(user -> user.getRoles() != null && user.getRoles().contains(role))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
                Set.of(user.getRole()));
    }

    @Override
    public List<String> getRolesByUserId(Long userId) {
        UserDto user = findUserById(userId);
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            throw new UserNotFoundException("No roles found for user with ID " + userId);
        }
        return user.getRoles();
    }

    @Override
    public List<UserDto> getUsers(int page, int size, String sortBy, String sortDir) {
        return users.stream()
                .sorted((u1, u2) -> {
                    if ("asc".equalsIgnoreCase(sortDir)) {
                        return u1.getName().compareToIgnoreCase(u2.getName());
                    } else {
                        return u2.getName().compareToIgnoreCase(u1.getName());
                    }
                })
                .skip((long) (page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersWithPaginationAndSorting(int page, int size, String sortBy, String sortDir) {
        return getUsers(page, size, sortBy, sortDir);
    }
}