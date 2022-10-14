package com.austin.Oshop.service;

import com.austin.Oshop.dto.*;
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
 * @created on 14/Oct/2022 - 1:36 AM
 * @project Oshop
 */
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity addProduct(ProductDto product) throws AlreadyExistException, NotFoundException {
        Optional<Product> productOptional =
                productRepository.findProductByName(product.getName());
        Optional<Category> categoryOptional =
                categoryRepository.findByName(product.getCategory());

        if (productOptional.isPresent()) {
            throw new AlreadyExistException("Product: " + product.getName() + " already exists");
        }
        if (!categoryOptional.isPresent()) {
            throw new NotFoundException("Category: " + product.getCategory() + " not found");
        }

        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setDescription(product.getDescription());
        newProduct.setCategory(categoryOptional.get());
        newProduct.setActive(true);
        newProduct.setImageUrl(product.getImageUrl());
        newProduct.setDateCreated(new Date());
        productRepository.save(newProduct);
        return ResponseEntity.ok(new HttpResponse(HttpStatus.OK.value(), "Product saved successfully!"));
    }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    public ResponseEntity deleteProduct(Long id) throws NotFoundException {
        Product product = productRepository.getById(id);
        if (product == null) {
            throw new NotFoundException("Product not found");
        }
        productRepository.delete(product);
        return ResponseEntity.ok(new HttpResponse(HttpStatus.OK.value(), "Product deleted"));
    }

}
