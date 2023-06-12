package net.azeti.challenge.recipe.recipe.controller;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.recipe.model.Recipe;
import net.azeti.challenge.recipe.recipe.service.RecipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    @GetMapping("/")
    public List<Recipe> getRecipes(){
        return recipeService.getAllRecipes();
    }
    @GetMapping("/{recipe_id}")
    public Optional<Recipe> getRecipeById(@PathVariable("recipe_id") Long recipeId){
        return recipeService.getRecipeById(recipeId);
    }
    @DeleteMapping("/{recipe_id}")
    public Optional<Recipe> deleteRecipeById(@PathVariable("recipe_id") Long recipeId){
        return recipeService.deleteRecipeById(recipeId);
    }
}
