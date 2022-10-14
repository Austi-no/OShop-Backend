package com.austin.Oshop.enums;

import static com.austin.Oshop.constant.Authority.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 23/Sep/2022 - 8:59 PM
 * @project Oshop
 */
public enum Role {
    ROLE_USER(USER_AUTHORITIES),
    ROLE_HR(HR_AUTHORITIES),
    ROLE_MANAGER(MANAGER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES),
    ROLE_SUPER_ADMIN(SUPER_ADMIN_AUTHORITIES);

    private final String[] authorities;

    Role(String... authorities) {
        this.authorities = authorities;
    }

    public String[] getAuthorities() {
        return authorities;
    }
}
