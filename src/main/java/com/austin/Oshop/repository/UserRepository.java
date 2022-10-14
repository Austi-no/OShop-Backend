package com.austin.Oshop.repository;

import com.austin.Oshop.model.*;
import org.springframework.data.jpa.repository.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 23/Sep/2022 - 11:33 PM
 * @project reservation
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);

    User findByEmail(String email);

    User findByUserId(String userId);


}
