package taco.com.tacocloud;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import taco.com.tacocloud.data.IngredientRepository;
import taco.com.tacocloud.tacos.Ingredient;

@Component
public class IngredientByIdConverter implements Converter<String,Ingredient> {
    
    private final IngredientRepository ingredientRepo;


    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
                }

    @Override
    public Ingredient convert(String id){
        return ingredientRepo.findById(id).orElse(null);
    }
    
}
