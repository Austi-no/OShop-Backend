package com.austin.Oshop.service;

import com.austin.Oshop.dto.*;
import com.austin.Oshop.exceptions.common.*;
import com.austin.Oshop.model.*;
import org.springframework.http.*;

import java.io.*;
import java.util.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 24/Sep/2022 - 12:41 PM
 * @project Oshop
 */
public interface UserService {

    ResponseEntity register(User user) throws NotFoundException, AlreadyExistException;

    List<User> getUsers();

    User findUserByUsername(String username) throws NotFoundException;

    User findUserByUserID(String userID) throws NotFoundException;

    User findUserByEmail(String email);

    User addNewUser(User user) throws NotFoundException, IOException,
            AlreadyExistException;

//    User updateUser(String currentUsername, String newFirstName, String newLastName, String newUsername, String newEmail, String role, boolean isNonLocked, boolean isActive, MultipartFile profileImage) throws UserNotFoundException, EmailAlreadyExistException, UsernameAlreadyExistException, MessagingException, IOException, NotAnImageFileException;

    void deleteUser(String username) throws IOException;

    ResponseEntity login(LoginRequest user);


}
