package com.example.demo.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SpringSecurity設定
 */
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 開発向け、h2DBコンソールは認証しない
                .mvcMatchers("/h2-console/**").permitAll()
                // ヘルスチェックURLは認証しない
                .mvcMatchers("/actuator/health").permitAll()
                // item削除画面はSYSTEMロールを保持しているユーザのみアクセス可能
                .mvcMatchers("/WBA0401/**").hasRole("SYSTEM")
                .anyRequest().authenticated()
                // ログイン
                .and().formLogin().defaultSuccessUrl("/", true)
                // ログアウト
                .and().logout((logout) -> logout.permitAll())
                // 開発向け、h2DBコンソール向け設定
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
}
