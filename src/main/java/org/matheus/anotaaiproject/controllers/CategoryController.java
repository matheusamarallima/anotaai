package org.matheus.anotaaiproject.controllers;

import org.matheus.anotaaiproject.entities.Category;
import org.matheus.anotaaiproject.entities.DTOs.CategoryDTO;
import org.matheus.anotaaiproject.repositories.CategoryRepository;
import org.matheus.anotaaiproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryService categoryService;

    @GetMapping
    public List<Category> categoryList(){
        return categoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryData){
        return ResponseEntity.ok(categoryService.create(categoryData));
    }
}
