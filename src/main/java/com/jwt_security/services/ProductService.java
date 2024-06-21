package com.jwt_security.services;

import com.jwt_security.dtos.ProductRequestDTO;
import com.jwt_security.dtos.ProductResponseDTO;
import com.jwt_security.entities.Product;
import com.jwt_security.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        var product = new Product(
                productRequestDTO.name(),
                productRequestDTO.price()
        );
        product = productRepository.save(product);
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    public List<ProductResponseDTO> getProducts() {
        var products = productRepository.findAll();
        List<ProductResponseDTO> listAllProducts = products.stream()
                .map(product -> new ProductResponseDTO(
                        product.getId().toString(),
                        product.getName(),
                        product.getPrice()
                )).toList();
        return listAllProducts;
    }
}
