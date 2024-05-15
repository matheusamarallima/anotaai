package org.matheus.anotaaiproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.matheus.anotaaiproject.entities.DTOs.CategoryDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "categories")
public class Category {

    @Id
    private String id;
    private String title;
    private String owner;
    private String description;

    public Category(CategoryDTO categoryDTO){
        this.title = categoryDTO.title();
        this.owner = categoryDTO.owner();
        this.description = categoryDTO.description();
    }

}
