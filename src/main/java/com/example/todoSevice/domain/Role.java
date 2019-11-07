package com.example.todoSevice.domain;

import org.springframework.security.core.GrantedAuthority;

/* Тут просто создается роль которая присваивается при регистрации */
/* Она как бы не нужна, но лучше пусть будет на всякий случай */
public enum Role implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
