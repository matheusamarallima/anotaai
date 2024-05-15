package org.matheus.anotaaiproject.repositories;

import org.matheus.anotaaiproject.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
