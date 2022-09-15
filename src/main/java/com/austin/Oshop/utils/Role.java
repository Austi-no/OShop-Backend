package com.austin.Oshop.utils;


import static com.austin.Oshop.constants.Authority.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 11/Aug/2022 - 12:02 AM
 * @project supportPortal
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
