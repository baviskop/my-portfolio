package com.long1dep.myportfolio.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableSpringDataWebSupport(
        pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO
) // hỗ trợ page ra JSON ổn định // annotation này thêm vào bất kỳ file config nào cũng được
public class SecurityBeansConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
