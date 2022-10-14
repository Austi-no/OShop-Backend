package com.austin.Oshop.repository;

import com.austin.Oshop.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 12/Oct/2022 - 12:11 AM
 * @project Oshop
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {


    Optional<Category>  findByName(String name);

    Category findCategoryById(Long id);

}
