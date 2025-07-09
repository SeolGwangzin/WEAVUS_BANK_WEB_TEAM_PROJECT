package com.weavus.weavus_bankweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean // ◀️ 주석 해제
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. CSRF 보호 비활성화
                .csrf(csrf -> csrf.disable())

                // 2. 모든 요청에 대해 접근 허용
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                )

                // 3. H2 콘솔을 위한 X-Frame-Options 비활성화
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }
}