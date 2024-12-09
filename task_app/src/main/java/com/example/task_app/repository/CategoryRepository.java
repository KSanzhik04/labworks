package com.example.task_app.repository;
import com.example.task_app.model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepository {

    private final List<Category> categories = new ArrayList<>();
    private Long categoryIdSequence = 1L;

    public List<Category> findAll() {
        return categories;
    }

    public Category save(Category category) {
        category.setId(categoryIdSequence++);
        categories.add(category);
        return category;
    }

    public Optional<Category> findById(Long id) {
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}

