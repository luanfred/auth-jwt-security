package com.jwt_security.controllers;

import com.jwt_security.dtos.ProductRequestDTO;
import com.jwt_security.repositories.ProductRepository;
import com.jwt_security.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductRequestDTO productRequestDTO) {
        try {
            var productResponse = this.productService.createProduct(productRequestDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getProducts() {
        try {
            var productsResponse = this.productService.getProducts();
            return ResponseEntity.ok().body(productsResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

