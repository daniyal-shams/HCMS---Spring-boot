package com.healthcare.user_service.service;

import org.springframework.stereotype.Service;

@Service
public interface UserDetailsService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;

}
