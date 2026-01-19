package com.long1dep.myportfolio.repository;

import com.long1dep.myportfolio.entity.RefreshToken;
import com.long1dep.myportfolio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}
