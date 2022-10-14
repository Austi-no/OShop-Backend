package com.austin.Oshop.controller;

import com.austin.Oshop.dto.*;
import com.austin.Oshop.exceptions.common.*;
import com.austin.Oshop.exceptions.global.*;
import com.austin.Oshop.model.*;
import com.austin.Oshop.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 24/Sep/2022 - 12:00 PM
 * @project Oshop
 */
@RestController
@RequestMapping(value = "/api/user")
@Tag(name = "User Controller", description = "User Controller")
@SecurityRequirement(name = "Authorization")
public class UserController extends ExceptionHandling {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }

    @Operation(summary = "Authenticate User", description = "Authenticate User")
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest user) throws NotFoundException {
        return userService.login(user);
    }


    @Operation(summary = "User Registration", description = "Create a new account")
    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody User user) throws NotFoundException, AlreadyExistException {
        return userService.register(user);
    }

    @Operation(summary = "List of Users", description = "List of Users")
//    @ApiResponses(value = {@ApiResponse(responseCode = "404", description = "Resource Not Found"), @ApiResponse(responseCode = "200", description = "Ok")})
    @GetMapping("/getAll")
    public List<User> listUsers() {
        return userService.getUsers();
    }


    @Operation(summary = "Get User by User ID", description = "Get User by Username")
    @GetMapping("/get/{userId}")
    public User getUser(@PathVariable("userId") String username) throws NotFoundException {
        return userService.findUserByUserID(username);
    }
}