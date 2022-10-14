package com.austin.Oshop.constant;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 24/Sep/2022 - 8:49 PM
 * @project Oshop
 */
public class SecurityContant {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String OSHOP_LTD = "OSHOP, LTD";
    public static final String OSHOP_ADMINISTRATION = "OSHOP Administration";
    public static final String AUTHORITIES = "authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
    public static final String[] PUBLIC_URLS = {"/api/user/login" ,"/api/user/register", "/v3/api-docs/**", "/swagger/**", "/api/auth/reset-password/**"};

}
