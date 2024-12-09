package com.example.task_app.controller;
import com.example.task_app.model.Category;
import com.example.task_app.repository.CategoryRepository;

import java.util.List;

public class CategoryController {

    private final CategoryRepository categoryRepository = new CategoryRepository();

    public String addCategory(String name) {
        Category category = new Category();
        category.setName(name);

        categoryRepository.save(category);
        return "Category added successfully";
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}

