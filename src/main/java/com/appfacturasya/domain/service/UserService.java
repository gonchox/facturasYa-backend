package com.appfacturasya.domain.service;

import com.appfacturasya.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    ResponseEntity<?> deleteUser(Long userId);

    User updateUser(Long userId, User userRequest);

    User createUser(User user);

    User getUserById(Long userId);

    Page<User> getAllUsers(Pageable pageable);

    User getUserByUsername(String username);
}
