package org.matheus.anotaaiproject.service;

import org.matheus.anotaaiproject.entities.Category;
import org.matheus.anotaaiproject.entities.DTOs.CategoryDTO;
import org.matheus.anotaaiproject.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Category create(CategoryDTO categoryData) {
        Category category = new Category(categoryData);
        return categoryRepository.save(category);
    }
}
