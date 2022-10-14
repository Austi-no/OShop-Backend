package com.austin.Oshop.repository;

import com.austin.Oshop.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 14/Oct/2022 - 1:35 AM
 * @project Oshop
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);

}
