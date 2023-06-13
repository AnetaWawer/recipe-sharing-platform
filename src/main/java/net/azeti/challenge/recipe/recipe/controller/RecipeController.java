package net.azeti.challenge.recipe.recipe.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.recipe.model.Recipe;
import net.azeti.challenge.recipe.recipe.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recipes")
public class RecipeController {
    private static final int MIN_OUTSIDE_TEMPERATURE_TO_GET_NON_BAKING_RECIPES = 20;
    private static final int MIN_OUTSIDE_TEMPERATURE_TO_AVOID_RECIPES_WITH_FROZEN_INGREDIENTS = 5;
    private final RecipeService recipeService;
    @GetMapping("/")
    public List<Recipe> getRecipes(){
        return recipeService.getAllRecipes();
    }
    @GetMapping("/{recipe_id}")
    public Optional<Recipe> getRecipeById(@PathVariable("recipe_id") Long recipeId){
        return recipeService.getRecipeById(recipeId);
    }
    @GetMapping("/users/{user_id}")
    public List<Recipe> getRecipesByUserId(@PathVariable("user_id") Long userId){
        return recipeService.getRecipesByUserId(userId);
    }
    @GetMapping("/users/search")
    public List<Recipe> getRecipesByUsername(@RequestParam(value = "username") String username){
        return recipeService.getRecipesByUsername(username);
    }
    @GetMapping("/search")
    public List<Recipe> getRecipesByTitle(@RequestParam(value = "title") String title){
        return recipeService.getRecipesByTitle(title);
    }
    @PostMapping("/")
    public ResponseEntity<Recipe> createRecipe(@RequestBody Recipe recipe, @AuthenticationPrincipal UserDetails user){
        return ResponseEntity.ok(recipeService.createRecipe(recipe, user));
    }

    @PutMapping("/{recipe_id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable("recipe_id") Long recipeId, @RequestBody Recipe recipe){
        return ResponseEntity.ok(recipeService.updateRecipe(recipeId, recipe));
    }
    @Transactional
    @DeleteMapping("/{recipe_id}")
    public Optional<Recipe> deleteRecipeById(@PathVariable("recipe_id") Long recipeId){
        return recipeService.deleteRecipeById(recipeId);
    }
    @GetMapping("/recommended")
    public List<Recipe> getRecommendedRecipes(@RequestParam(value = "temperature") int temperature){
        if(temperature>=MIN_OUTSIDE_TEMPERATURE_TO_GET_NON_BAKING_RECIPES){
            return recipeService.getRecipesNotRequiringBaking();
        } else if(temperature<=MIN_OUTSIDE_TEMPERATURE_TO_AVOID_RECIPES_WITH_FROZEN_INGREDIENTS){
            return recipeService.getRecipesNotUsingFrozenIngredients();
        } else {
            return recipeService.getAllRecipes();
        }
    }
}
