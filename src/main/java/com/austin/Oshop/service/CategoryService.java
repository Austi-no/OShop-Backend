package com.austin.Oshop.service;

import com.austin.Oshop.exceptions.common.*;
import com.austin.Oshop.model.*;
import com.austin.Oshop.repository.*;
import com.austin.Oshop.utils.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.util.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 12/Oct/2022 - 12:13 AM
 * @project Oshop
 */
@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity addCategory(Category category) throws AlreadyExistException {
        Optional <Category> categoryOptional = categoryRepository.findByName(category.getName());
        if (categoryOptional.isPresent()) {
            throw new AlreadyExistException("Category: " + category.getName() + " already exists");
        }
        categoryRepository.save(category);
        return ResponseEntity.ok(new HttpResponse(HttpStatus.OK.value(), "Category saved"));
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    public  ResponseEntity deleteCategory(Long id) throws NotFoundException {
        Category category = categoryRepository.findCategoryById(id);
        if (category == null) {
            throw new NotFoundException("Category not found");
        }
        categoryRepository.delete(category);
        return ResponseEntity.ok(new HttpResponse(HttpStatus.OK.value(), "Category deleted"));
    }

    private Category validateCategory(String name) throws AlreadyExistException {
        Optional<Category> category = categoryRepository.findByName(name);

        if (category.isPresent()) {
            throw new AlreadyExistException("Category: " + name + " Already exists");
        } else {
            return category.get();
        }

    }


}
