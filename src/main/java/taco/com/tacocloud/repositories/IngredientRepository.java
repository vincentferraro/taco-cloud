package taco.com.tacocloud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import taco.com.tacocloud.models.Ingredient;


public interface IngredientRepository extends CrudRepository<Ingredient,String> {
    
    
}
