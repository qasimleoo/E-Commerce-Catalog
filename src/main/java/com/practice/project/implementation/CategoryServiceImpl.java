package com.practice.project.implementation;

import com.practice.project.entity.Category;
import com.practice.project.dao.CategoryRepo;
import com.practice.project.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepo categoryRepo;
    @Autowired
    public CategoryServiceImpl(CategoryRepo theCategoryRepo) {
        categoryRepo = theCategoryRepo;
    }
    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }
    public void addCategory(Category category){
        categoryRepo.save(category);
    }
    public void removeCategoryById(int id){
        categoryRepo.deleteById(id);
    }
    public Optional<Category> getCategoryById(int id){
        return categoryRepo.findById(id);
    }
}
