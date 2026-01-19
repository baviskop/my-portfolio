package com.long1dep.myportfolio.service;

import com.long1dep.myportfolio.dto.request.RegisterRequest;
import com.long1dep.myportfolio.entity.Role;
import com.long1dep.myportfolio.entity.User;
import com.long1dep.myportfolio.repository.RoleRepository;
import com.long1dep.myportfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setRoles(Set.of(userRole));

        userRepository.save(user);
    }
}
