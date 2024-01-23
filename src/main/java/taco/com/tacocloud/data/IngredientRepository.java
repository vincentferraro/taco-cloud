package taco.com.tacocloud.data;

import taco.com.tacocloud.tacos.Ingredient;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface IngredientRepository extends CrudRepository<Ingredient,String> {
    
}
