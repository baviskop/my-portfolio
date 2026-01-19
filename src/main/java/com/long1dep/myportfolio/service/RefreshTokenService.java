package com.long1dep.myportfolio.service;

import com.long1dep.myportfolio.entity.RefreshToken;
import com.long1dep.myportfolio.entity.User;
import com.long1dep.myportfolio.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepo;

    // check : xóa rt cũ, tạo mới
    public RefreshToken create(User user) {
        refreshTokenRepo.deleteByUser(user);

        RefreshToken rt = new RefreshToken();
        rt.setUser(user);
        rt.setToken(UUID.randomUUID().toString());
        rt.setExpiryDate(LocalDateTime.now().plusDays(14));

        return refreshTokenRepo.save(rt);
    }

    public RefreshToken verify(String token) {
        RefreshToken rt = refreshTokenRepo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if(rt.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepo.delete(rt);
            throw new RuntimeException("Refresh token expired");
        }
        return rt;
    }

}
