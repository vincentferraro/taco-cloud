package taco.com.tacocloud.models;

import lombok.Data;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Data
@Entity
public class Taco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    
    @NotNull
    @Size(min=4, message="Name must be at least 5 characters long")
    private String name;
    
    private Date createdAt = new Date();
    
    @NotNull
    @ManyToMany
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    // GETTERS AND SETTERS with Lambo

    public void addIngredients(Ingredient ingredient){
        this.ingredients.add(ingredient);
    }
}
