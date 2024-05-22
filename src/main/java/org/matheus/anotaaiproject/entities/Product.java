package org.matheus.anotaaiproject.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
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
    private String title;
    private String owner;
    private Category category;
    private double price;
    private String description;

    public Product(ProductDTO productDTO) {
        this.owner = productDTO.owner();
        this.title = productDTO.title();
        this.category = productDTO.category();
        this.price = productDTO.price();
        this.description = productDTO.description();
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", this.title);
        jsonObject.put("description", this.description);
        jsonObject.put("category", this.category);
        jsonObject.put("owner", this.owner);
        jsonObject.put("type", "product");
        return jsonObject.toString();
    }
}
