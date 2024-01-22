package taco.com.tacocloud.data;

import taco.com.tacocloud.tacos.Ingredient;
import java.util.Optional;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();

    Optional<Ingredient> findById(String id);

    Ingredient save(Ingredient ingredient);
    
}
