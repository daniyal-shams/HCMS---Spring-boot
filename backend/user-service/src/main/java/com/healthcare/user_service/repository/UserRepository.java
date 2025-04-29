package com.healthcare.user_service.repository;

import com.healthcare.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  <Optional> User findByUserName(String username);
  <Optional> User findByEmail(String email);
}
