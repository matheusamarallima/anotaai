package org.matheus.anotaaiproject.service;

import lombok.RequiredArgsConstructor;
import org.matheus.anotaaiproject.entities.Category;
import org.matheus.anotaaiproject.entities.Product;
import org.matheus.anotaaiproject.entities.DTOs.ProductDTO;
import org.matheus.anotaaiproject.repositories.CategoryRepository;
import org.matheus.anotaaiproject.repositories.ProductRepository;
import org.matheus.anotaaiproject.service.Aws.AwsSnsService;
import org.matheus.anotaaiproject.service.Aws.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final ProductRepository ProductRepository;
    private final CategoryRepository categoryRepository;
    private final AwsSnsService awsSnsService;

    public Product create(ProductDTO ProductData) {
        Product product = new Product(ProductData);
        Optional<Category> categoryOptional = categoryRepository.findById(product.getCategory().getId());
        if(categoryOptional.isPresent()){
            awsSnsService.publish(new MessageDTO(product.toString()));
            return ProductRepository.save(product);
        }
        categoryRepository.save(product.getCategory());
        return ProductRepository.save(product);
    }

    public List<Product> findAll() {
        return ProductRepository.findAll();
    }

    public ResponseEntity<Product> fixProduct(String id, ProductDTO Product) {
        Optional<Product> ProductFound = ProductRepository.findById(id);
        if(ProductFound.isPresent()){
            Product product1 = new Product(Product);
            product1.setId(id);
            Optional<Category> categoryOptional = categoryRepository.findById(product1.getCategory().getId());
            if(categoryOptional.isPresent()){
                awsSnsService.publish(new MessageDTO(product1.toString()));
                ProductRepository.save(product1);
                return ResponseEntity.ok(product1);
            }
            categoryRepository.save(product1.getCategory());
            ProductRepository.save(product1);

            awsSnsService.publish(new MessageDTO(product1.toString()));
            return ResponseEntity.ok(product1);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<String> deleteProduct(String id) {
        Optional<Product> toBeDeleted = ProductRepository.findById(id);
        if (toBeDeleted.isPresent()) {
            ProductRepository.deleteById(id);
            return ResponseEntity.ok().body("Deleted");
        }
        return ResponseEntity.notFound().build();
    }
}
