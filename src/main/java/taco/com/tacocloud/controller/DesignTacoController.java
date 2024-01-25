package taco.com.tacocloud.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import taco.com.tacocloud.models.Ingredient;
import taco.com.tacocloud.models.Taco;
import taco.com.tacocloud.models.TacoOrder;
import taco.com.tacocloud.models.Ingredient.Type;
import taco.com.tacocloud.repositories.IngredientRepository;

import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    
    private final IngredientRepository ingredientRepo;

    @Autowired
    DesignTacoController(IngredientRepository ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();

      Type [] types = Ingredient.Type.values();

      for(Type type : types){
        model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
         
      }
    }

    @ModelAttribute(name="tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name="taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String addTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if(errors.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing info : {}", taco);
        
        return "redirect:/orders/current";
    }
    

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type){
        return StreamSupport.stream(ingredients.spliterator(), false).filter(x->x.getType().equals(type)).collect(Collectors.toList());
    }
}
