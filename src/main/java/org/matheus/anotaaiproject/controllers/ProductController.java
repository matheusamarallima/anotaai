package org.matheus.anotaaiproject.controllers;

import org.matheus.anotaaiproject.entities.DTOs.ProductDTO;
import org.matheus.anotaaiproject.entities.Product;
import org.matheus.anotaaiproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {


    @Autowired
    ProductService ProductService;

    @GetMapping
    public ResponseEntity<List<Product>> ProductList() {
        return ResponseEntity.ok().body(ProductService.findAll());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO ProductData) {
        Product Product = ProductService.create(ProductData);
        return ResponseEntity.ok().body(Product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> fixProduct(@RequestBody ProductDTO ProductData, @PathVariable("id") String id){
        return ProductService.fixProduct(id, ProductData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id){
        return ProductService.deleteProduct(id);
    }
}
