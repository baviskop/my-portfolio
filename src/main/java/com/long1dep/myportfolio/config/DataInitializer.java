package com.long1dep.myportfolio.config;

import com.long1dep.myportfolio.entity.Role;
import com.long1dep.myportfolio.entity.User;
import com.long1dep.myportfolio.repository.RoleRepository;
import com.long1dep.myportfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData(RoleRepository roleRepo, UserRepository userRepo) {
        return args -> {
            Role adminRole = roleRepo.findByName("ADMIN")
                    .orElseGet(() -> roleRepo.save(new Role(null, "ADMIN")));

            Role userRole = roleRepo.findByName("USER")
                    .orElseGet(() -> roleRepo.save(new Role(null, "USER")));

            if (!userRepo.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("123456"));
                admin.setEnabled(true);
                admin.setRoles(Set.of(adminRole, userRole));

                userRepo.save(admin);
            }
        };
    }
}
