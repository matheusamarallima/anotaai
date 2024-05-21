package org.matheus.anotaaiproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.matheus.anotaaiproject.entities.DTOs.ProductDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String owner;
    private Category category;
    private double price;
    private String description;

    public Product(ProductDTO productDTO) {
        this.owner = productDTO.owner();
        this.category = productDTO.category();
        this.price = productDTO.price();
        this.description = productDTO.description();
    }
}
