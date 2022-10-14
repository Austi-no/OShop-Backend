package com.austin.Oshop.controller;

import com.austin.Oshop.exceptions.common.*;
import com.austin.Oshop.model.*;
import com.austin.Oshop.service.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.security.*;
import io.swagger.v3.oas.annotations.tags.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author BENARD AUGUSTINE ADAKOLE
 * @created on 12/Oct/2022 - 12:29 AM
 * @project Oshop
 */
@RestController
@RequestMapping(value = "/api/category")
@Tag(name = "Category Controller", description = "Category Controller")
@SecurityRequirement(name = "Authorization")
public class CategoryController {

    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "Add Category", description = "Add new category")
    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody Category category) throws AlreadyExistException {
        return categoryService.addCategory(category);
    }

    @Operation(summary = "Get all categories", description = "Get all categories")
    @GetMapping("/getAll")
    public List<Category> getAllCategories() {
        return categoryService.getCategoryList();
    }

    @Operation(summary="Delete category", description = "Delete category")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") Long id) throws NotFoundException {
        return categoryService.deleteCategory(id);
    }


}
