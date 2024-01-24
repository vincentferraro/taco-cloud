package taco.com.tacocloud.models;


import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class IngredientRef {
    
    private final String ingredient;
}
