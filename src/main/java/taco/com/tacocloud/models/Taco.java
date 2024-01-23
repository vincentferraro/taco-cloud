package taco.com.tacocloud.tacos;

import lombok.Data;
import java.util.List;
import java.util.Date;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table
public class Taco {
    
    @Id
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=4, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    // GETTERS AND SETTERS with Lambo
}