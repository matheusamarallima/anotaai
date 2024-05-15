package org.matheus.anotaaiproject.repositories;

import org.matheus.anotaaiproject.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
