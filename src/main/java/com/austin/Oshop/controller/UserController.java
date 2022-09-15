package com.austin.Oshop.controller;

import com.austin.Oshop.config.*;
import com.austin.Oshop.exceptions.*;
import com.austin.Oshop.model.*;
import com.austin.Oshop.service.*;
import com.austin.Oshop.utils.*;
import org.apache.logging.log4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

import static com.austin.Oshop.constants.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 15/Sep/2022 - 1:19 AM
 * @project Oshop
 */
@RestController
@RequestMapping(path = {"/user"})
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<HttpResponse> register(@RequestBody RegisterRequest request) throws UserNotFoundException, EmailAlreadyExistException, UsernameAlreadyExistException {

       userService.register(request);
       return response(OK, "User registered successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws UserNotFoundException {
        authenticate(user.getUsername(), user.getPassword());
        User loginUser = userService.findUserByUsername(user.getUsername());
        System.out.println(loginUser);
        UserPrincipal userPrincipal = new UserPrincipal(loginUser);
        HttpHeaders jwtHeaders = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeaders, OK);
    }


    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtProvider.generateToken(user));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }
}
