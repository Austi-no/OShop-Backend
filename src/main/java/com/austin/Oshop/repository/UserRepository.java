package com.austin.Oshop.repository;

import com.austin.Oshop.model.*;
import org.springframework.data.jpa.repository.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 15/Sep/2022 - 12:14 AM
 * @project Oshop
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    User findUserById(Long id);

    User findUserByEmail(String email);

}
