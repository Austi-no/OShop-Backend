package com.austin.Oshop.listener;

import com.austin.Oshop.service.*;
import org.springframework.context.event.*;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 11/Aug/2022 - 10:20 PM
 * @project supportPortal
 */
@Component
public class AuthenticationFailureListener {
    private final LoginAttemptService loginAttemptService;

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
