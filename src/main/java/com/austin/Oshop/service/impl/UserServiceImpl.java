package com.austin.Oshop.service.impl;

import com.austin.Oshop.config.security.*;
import com.austin.Oshop.dto.*;
import com.austin.Oshop.enums.*;
import com.austin.Oshop.exceptions.common.*;
import com.austin.Oshop.model.User;
import com.austin.Oshop.repository.*;
import com.austin.Oshop.service.*;
import com.austin.Oshop.utils.*;
import org.apache.commons.lang3.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.io.*;
import java.util.*;

import static com.austin.Oshop.constant.SecurityContant.*;
import static com.austin.Oshop.constant.UserConstant.*;
import static org.springframework.http.HttpStatus.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 24/Sep/2022 - 11:48 AM
 * @project booking
 */
@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final BCryptPasswordEncoder passwordEncoder;
    private final LoginAttemptService loginAttemptService;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, LoginAttemptService loginAttemptService, AuthenticationManager authenticationManager, JWTProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginAttemptService = loginAttemptService;

        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    //    AUTHENTICATE AND LOGIN USER
    @Override
    public ResponseEntity<User> login(LoginRequest user) {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = findUserByUsername(user.getUsername());
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeaders = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeaders, OK);

    }

    //    REGISTER NEW USER
    @Override
    public ResponseEntity register(User user) throws NotFoundException, AlreadyExistException {
        validateNewUsernameAndEmail(StringUtils.EMPTY, user.getUsername(), user.getEmail());
        user.setUserId("OS" + generateUserId());
        user.setEmail(user.getEmail());
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setUsername(user.getUsername());
        user.setPlainPassword(user.getPassword());
        user.setPassword(encodePassword(user.getPassword()));
        user.setJoinDate(new Date());
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse<>(OK.value(), "User registered successfully", savedUser));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error(USER_NOT_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(USER_NOT_FOUND_BY_USERNAME + username);
        } else {
            validateLoginAttempts(user);
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);

            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info(FOUND_USER_BY_USERNAME + username);
            return userPrincipal;
        }
    }

    private void validateLoginAttempts(User user) {

        if (user.isNotLocked()) {
            user.setNotLocked(!loginAttemptService.hasExceededMaxAttempts(user.getUsername()));
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }


    private Role getRoleEnumName(String role) {
        return Role.valueOf(role.toUpperCase());
    }


    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private String generateUserId() {
        return RandomStringUtils.randomNumeric(6);
    }


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByUserID(String userID) throws NotFoundException {
        User existing = userRepository.findByUserId(userID);
        if (existing == null) {
            throw new NotFoundException("No user found by userid: " + userID);
        }
        return userRepository.findByUserId(userID);
    }


    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User addNewUser(User user) throws NotFoundException, IOException, AlreadyExistException {
        return null;
    }

    @Override
    public void deleteUser(String username) throws IOException {

    }


    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtProvider.generateToken(user));
        return headers;
    }

    private void authenticate(String username, String password) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }


    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail)
            throws NotFoundException, AlreadyExistException {
        User userByNewUsername = findUserByUsername(newUsername);
        User userByNewEmail = findUserByEmail(newEmail);
        if (StringUtils.isNotBlank(currentUsername)) {
            User currentUser = findUserByUsername(currentUsername);
            if (currentUser == null) {
                throw new NotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if (userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new AlreadyExistException(USERNAME_ALREADY_EXIST);
            }
            if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new AlreadyExistException(EMAIL_ALREADY_EXIST);
            }
            return currentUser;
        } else {
            if (userByNewUsername != null) {
                throw new AlreadyExistException(USERNAME_ALREADY_EXIST);
            }
            if (userByNewEmail != null) {
                throw new AlreadyExistException(EMAIL_ALREADY_EXIST);
            }
            return null;
        }
    }
}
