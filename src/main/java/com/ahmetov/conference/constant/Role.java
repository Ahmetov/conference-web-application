package com.ahmetov.conference.constant;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, PRESENTER;

    @Override
    public String getAuthority() {
        return name();
    }
}
