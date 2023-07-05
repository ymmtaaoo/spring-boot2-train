package com.example.demo.core.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 本番用　未実装
 * DBで管理しているユーザ情報を取得するクラス
 * 
 * application.propertiesファイルに「web.security.db.auth=true」の場合に本クラスが動作する。
 */
@Component
@ConditionalOnProperty(value = "web.security.db.auth")
public class DbUserDetailsService implements UserDetailsService {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 本番向けの強度をデフォルト10から13に変更
        return new BCryptPasswordEncoder(13);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails user = findUserFromDb(username);
    
        if (user == null) {
            // 存在しないユーザ
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    /**
     * ユーザ名に合致するユーザを取得する
     * 未実装
     */
    private UserDetails findUserFromDb(String username) {

        // 未実装：ユーザ管理テーブルからユーザ情報を取得する。

        return User.builder()
            .username(username)
            .password(passwordEncoder().encode(username + username))
            .roles("USER")
            .build();
    }
    
}
