package org.matheus.anotaaiproject.service;

import lombok.RequiredArgsConstructor;
import org.matheus.anotaaiproject.entities.Category;
import org.matheus.anotaaiproject.entities.DTOs.CategoryDTO;
import org.matheus.anotaaiproject.repositories.CategoryRepository;
import org.matheus.anotaaiproject.service.Aws.AwsSnsService;
import org.matheus.anotaaiproject.service.Aws.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;
    private final AwsSnsService awsSnsService;

    public Category create(CategoryDTO categoryData) {
        Category category = new Category(categoryData);
        awsSnsService.publish(new MessageDTO(category.toString()));
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<Category> fixCategory(String id, CategoryDTO category) {
        Optional<Category> categoryFound = categoryRepository.findById(id);
        if(categoryFound.isPresent()){
            Category category1 = new Category(category);
            category1.setId(id);
            awsSnsService.publish(new MessageDTO(category1.toString()));
            categoryRepository.save(category1);
            return ResponseEntity.ok(category1);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> deleteCategory(String id) {
        Optional<Category> toBeDeleted = categoryRepository.findById(id);
        if (toBeDeleted.isPresent()) {
            categoryRepository.deleteById(id);
           return ResponseEntity.ok().body("Deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
