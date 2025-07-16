package com.weavus.weavus_bankweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Spring Securityを有効にします。
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 暗号化を使用せず、平文のまま比較する方式（セキュリティ上、非常に脆弱なため開発・学習用）
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF保護機能（後で有効化することを推奨）
                .csrf(csrf -> csrf.disable())

                // HTTPリクエストに対するアクセス権限の設定
                .authorizeHttpRequests(auth -> auth
                        // 以下のパスは誰でもアクセスできるように許可します。
                        .requestMatchers("/login", "/register", "/myPage", "/css/**", "/js/**", "/image/**").permitAll()
                        // 上記以外のすべてのリクエストは、必ず認証（ログイン）を経る必要があります。
                        .anyRequest().authenticated()
                )


                // フォームベースのログイン設定
                .formLogin(form -> form
                        .loginPage("/login") // 自作のカスタムログインページのパス
                        .loginProcessingUrl("/login") // ログインフォームのaction URL（Spring Securityがこのリクエストを処理します）
                        .defaultSuccessUrl("/index", true) // ログイン成功時にリダイレクトするデフォルトページ
                        .permitAll() // ログインページ自体は誰でもアクセス可能でなければなりません。
                        .failureUrl("/login?error")
                )

                // ログアウト設定
                .logout(logout -> logout
                        .logoutUrl("/logout") // ログアウトを処理するURL
                        .logoutSuccessUrl("/login") // ログアウト成功後にリダイレクトするページ
                        .invalidateHttpSession(true) // ログアウト時にHTTPセッションを無効化します。
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));
        return http.build();
    }
}