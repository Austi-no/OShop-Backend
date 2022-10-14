package com.austin.Oshop.listener;

import com.austin.Oshop.service.*;
import org.springframework.context.event.*;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 24/Sep/2022 - 2:27 AM
 * @project Oshop
 */
@Component
public class AuthenticationFailureListener {
    private LoginAttemptService loginAttemptService;

    public AuthenticationFailureListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @EventListener
    public void onAuthenticationFailure(AuthenticationFailureBadCredentialsEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof String) {
            String username = (String) event.getAuthentication().getPrincipal();
            loginAttemptService.addUserToLoginAttemptCache(username);
        }
    }
}