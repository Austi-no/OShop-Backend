package com.austin.Oshop.service;

import com.austin.Oshop.exceptions.*;
import com.austin.Oshop.model.*;
import com.austin.Oshop.model.User;
import com.austin.Oshop.utils.*;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 15/Sep/2022 - 12:20 AM
 * @project Oshop
 */
public interface UserService {


    ResponseEntity<User> register(RegisterRequest request) throws UserNotFoundException, EmailAlreadyExistException, UsernameAlreadyExistException;

     List<User> getUsers();

     User findUserByUsername(String username);

     User findUserByEmail(String email);

     void resetPassword(String email);

}
