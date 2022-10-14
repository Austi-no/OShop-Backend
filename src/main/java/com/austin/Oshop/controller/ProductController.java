package com.austin.Oshop.controller;

import com.austin.Oshop.dto.*;
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
 * @created on 14/Oct/2022 - 1:41 AM
 * @project Oshop
 */
@RestController
@RequestMapping(value = "/api/product")
@Tag(name = "Product Controller", description = "Product Controller")
@SecurityRequirement(name = "Authorization")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Add Product", description = "Add new product")
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductDto product) throws AlreadyExistException, NotFoundException {
        return productService.addProduct(product);
    }

    @Operation(summary = "Get all products", description = "Get all products")
    @GetMapping("/getAll")
    public List<Product> getAllProducts() {
        return productService.getProductList();
    }

    @Operation(summary="Delete product", description = "Delete product")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) throws NotFoundException {
        return productService.deleteProduct(id);
    }

}
