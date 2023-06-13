package net.azeti.challenge.recipe.recipe.service;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.recipe.model.Ingredients;
import net.azeti.challenge.recipe.recipe.model.Recipe;
import net.azeti.challenge.recipe.recipe.repository.IngredientsRepository;
import net.azeti.challenge.recipe.recipe.repository.RecipeRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientsRepository ingredientsRepository;
    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll();
    }
    public Optional<Recipe> getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId);
    }
    public List<Recipe> getRecipesByUsername(String username) {
        return recipeRepository.findAllByUsername(username);
    }
    public List<Recipe> getRecipesByTitle(String title) {
        return recipeRepository.findAllByTitle(title);
    }
    public Recipe createRecipe(Recipe recipe, UserDetails user) {
        Recipe newRecipe = new Recipe();
        newRecipe.setDescription(recipe.getDescription());
        newRecipe.setInstructions(recipe.getInstructions());
        newRecipe.setServings(recipe.getServings());
        newRecipe.setTitle(recipe.getTitle());
        newRecipe.setUsername(user.getUsername());
        newRecipe.setIngredients(recipe.getIngredients());
        List<Ingredients> newIngredients = recipe.getIngredients();
        recipeRepository.save(newRecipe);
        for (Ingredients newIngredient : newIngredients) {
            newIngredient.setRecipe(newRecipe);
        }
        ingredientsRepository.saveAll(newIngredients);
        return newRecipe;
    }
    public Recipe updateRecipe(Long recipeId, Recipe recipe) {
        Recipe updatedRecipe = recipeRepository.findById(recipeId).orElseThrow();
        List<Ingredients> ingredients = ingredientsRepository.findAllByRecipeId(recipeId);
        updatedRecipe.setTitle(recipe.getTitle());
        updatedRecipe.setDescription(recipe.getDescription());
        updatedRecipe.setServings(recipe.getServings());
        updatedRecipe.setInstructions(recipe.getInstructions());
        for (Ingredients ingredient : recipe.getIngredients()) {
            ingredient.setRecipe(updatedRecipe);
        }
        ingredientsRepository.saveAll(recipe.getIngredients());
        recipeRepository.save(updatedRecipe);
        ingredientsRepository.deleteAll(ingredients);
        return updatedRecipe;
    }
    public Optional<Recipe> deleteRecipeById(Long recipeId) {
        Optional<Recipe> recipeToDelete = getRecipeById(recipeId);
        ingredientsRepository.deleteAllByRecipeId(recipeId);
        recipeRepository.deleteById(recipeId);
        return recipeToDelete;
    }
    public List<Recipe> getRecipesNotRequiringBaking(){
        return recipeRepository.findAllNonBakedRecipes();
    }
    public List<Recipe> getRecipesNotUsingFrozenIngredients(){
        return recipeRepository.findAllWithNonFrozenIngredients();
    }
}
