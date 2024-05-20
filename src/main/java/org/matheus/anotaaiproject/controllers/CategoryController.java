package org.matheus.anotaaiproject.controllers;

import org.matheus.anotaaiproject.entities.Category;
import org.matheus.anotaaiproject.entities.DTOs.CategoryDTO;
import org.matheus.anotaaiproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> categoryList() {
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryData) {
        Category category = categoryService.create(categoryData);
        return ResponseEntity.ok().body(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> fixCategory(@RequestBody CategoryDTO categoryData, @PathVariable("id") String id){
        return categoryService.fixCategory(id, categoryData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable String id){
        return categoryService.deleteCategory(id);
    }
}
