package com.long1dep.myportfolio.controller.publicapi;

import com.long1dep.myportfolio.dto.request.LoginRequest;
import com.long1dep.myportfolio.dto.request.RegisterRequest;
import com.long1dep.myportfolio.dto.response.LoginResponse;
import com.long1dep.myportfolio.dto.response.RegisterResponse;
import com.long1dep.myportfolio.security.JwtTokenProvider;
import com.long1dep.myportfolio.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RegisterResponse("Registered Successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request, SessionStatus sessionStatus) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String token = tokenProvider.generateToken(authentication.getName());
        return ResponseEntity.ok(new LoginResponse(token));
    }
}
