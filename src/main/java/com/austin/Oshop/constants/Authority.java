package com.austin.Oshop.constants;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 10/Aug/2022 - 11:50 PM
 * @project supportPortal
 */
public class Authority {
    public static final String[] USER_AUTHORITIES = { "user:read" };
    public static final String[] HR_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] MANAGER_AUTHORITIES = { "user:read", "user:update" };
    public static final String[] ADMIN_AUTHORITIES = { "user:read", "user:create", "user:update" };
    public static final String[] SUPER_ADMIN_AUTHORITIES = { "user:read", "user:create", "user:update", "user:delete" };
}
