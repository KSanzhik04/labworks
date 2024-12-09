package com.example.task_app.service;
import com.example.task_app.model.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;
public class CategoryService {

    private List<Category> categories = new ArrayList<>();
    private Long categoryIdSequence = 1L;

    public List<Category> getAllCategories() {
        return categories;
    }

    public Category createCategory(String name) {
        Category category = new Category();
        category.setId(categoryIdSequence++);
        category.setName(name);
        categories.add(category);
        return category;
    }

    public Optional<Category> findById(Long id) {
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst();
    }
}

