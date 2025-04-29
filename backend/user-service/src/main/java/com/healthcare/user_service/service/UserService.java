package com.healthcare.user_service.service;

import org.springframework.stereotype.Service;

import com.healthcare.user_service.dto.UserDto;
import java.util.List;

@Service
public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    UserDto updateUser(Long id, UserDto userDto);

    void deleteUser(Long id);

    List<UserDto> getAllUsers();

    List<UserDto> getUsersByRole(String role);

    UserDto getUserByUsername(String username);

    List<String> getRolesByUserId(Long userId);

    List<UserDto> getUsers(int page, int size, String sortBy, String sortDir);

    List<UserDto> getUsersWithPaginationAndSorting(int page, int size, String sortBy, String sortDir);
}
