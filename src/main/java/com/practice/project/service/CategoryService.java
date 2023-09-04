package com.practice.project.service;

import com.practice.project.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategory();
    void addCategory(Category category);
    void removeCategoryById(int id);
    Optional<Category> getCategoryById(int id);
}
