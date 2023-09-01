package com.practice.project.service;

import com.practice.project.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    void addProduct(Product product);
    void removeProductById(Long id);
    Optional<Product> getProductById(Long id);
    List<Product> getProductsByCategoryId(int id);
}
