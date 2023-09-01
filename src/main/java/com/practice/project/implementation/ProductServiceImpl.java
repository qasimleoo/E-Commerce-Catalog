package com.practice.project.implementation;

import com.practice.project.entity.Product;
import com.practice.project.dao.ProductRepo;
import com.practice.project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepo productRepo;
    @Autowired
    public ProductServiceImpl(ProductRepo theProductRepo) {
        productRepo = theProductRepo;
    }
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
    public void addProduct(Product product){
        productRepo.save(product);
    }
    public void removeProductById(Long id){
        productRepo.deleteById(id);
    }
    public Optional<Product> getProductById(Long id){
        return productRepo.findById(id);
    }
    public List<Product> getProductsByCategoryId(int id){
        return productRepo.findAllByCategory_Id(id);
    }
}
