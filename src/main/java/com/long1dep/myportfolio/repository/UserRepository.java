package com.long1dep.myportfolio.repository;

import com.long1dep.myportfolio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
