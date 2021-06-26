package com.appfacturasya.service;

import com.appfacturasya.domain.model.User;
import com.appfacturasya.domain.repository.UserRepository;
import com.appfacturasya.domain.service.UserService;
import com.appfacturasya.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_USERNAME = "admin";
    private static final List<GrantedAuthority> DEFAULT_AUTHORITIES = new ArrayList<>();

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public User updateUser(Long userId, User userRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setName(userRequest.getName());
        return userRepository.save(user);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return user;
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Username", username));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String defaultPassword = passwordEncoder.encode("admin");
        if(DEFAULT_USERNAME.equals(username)) {
            return new org.springframework.security.core.userdetails.User(DEFAULT_USERNAME, defaultPassword, DEFAULT_AUTHORITIES);
        }
        throw new UsernameNotFoundException(String.format("User not found with username %s", username));
    }
}
