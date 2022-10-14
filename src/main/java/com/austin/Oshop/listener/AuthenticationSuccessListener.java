package com.austin.Oshop.listener;

import com.austin.Oshop.service.*;
import com.austin.Oshop.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.event.*;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 24/Sep/2022 - 2:28 AM
 * @project Oshop
 */
@Component
public class AuthenticationSuccessListener {
    private final LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @EventListener
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof UserPrincipal) {
            UserPrincipal user = (UserPrincipal) event.getAuthentication().getPrincipal();
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }
}
