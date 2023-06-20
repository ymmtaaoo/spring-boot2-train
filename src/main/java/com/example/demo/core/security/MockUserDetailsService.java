package com.example.demo.core.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 開発用モッククラス
 */
@Component
@ConditionalOnProperty(value = "web.security.db.auth", havingValue ="false" , matchIfMissing = true)
public class MockUserDetailsService implements UserDetailsService {

    PasswordEncoder passwordEncoder = new Pbkdf2PasswordEncoder();

    /**
     * ダミーユーザを生成する。
     * ユーザ名とパスワードが同じ。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("root".equals(username)) {
            return User.builder()
                .username(username)
                .password(passwordEncoder.encode(username))
                .roles("USER","SYSTEM")
                .build();
        }
        return User.builder()
            .username(username)
            .password(passwordEncoder.encode(username))
            .roles("USER")
            .build();
    }
}
