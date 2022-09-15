package com.austin.Oshop.config.filter;

import com.austin.Oshop.config.*;
import org.springframework.http.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import static com.austin.Oshop.constants.SecurityConstant.OPTIONS_HTTP_METHOD;
import static com.austin.Oshop.constants.SecurityConstant.TOKEN_PREFIX;
import static org.springframework.http.HttpHeaders.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 08/Aug/2022 - 1:41 PM
 * @project supportPortal
 */
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final JWTProvider jwtProvider;

    public JWTAuthorizationFilter(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase(OPTIONS_HTTP_METHOD)) {
            response.setStatus(HttpStatus.OK.value());
        } else {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = authorizationHeader.substring(TOKEN_PREFIX.length());
            String username = jwtProvider.getSubject(token);
            if (jwtProvider.isTokenValid(username, token) && SecurityContextHolder.getContext().getAuthentication() == null) {
                List<GrantedAuthority> authorities = jwtProvider.getAuthorities(token);
                Authentication authentication = jwtProvider.getAuthentication(username, authorities, request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }
}
