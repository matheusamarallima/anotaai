package org.matheus.anotaaiproject.entities.DTOs;

import org.matheus.anotaaiproject.entities.Category;

public record ProductDTO(String title, String owner, Category category, Double price, String description) {
}
