package net.azeti.challenge.recipe.recipe.service;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.recipe.model.Recipe;
import net.azeti.challenge.recipe.recipe.repository.IngredientsRepository;
import net.azeti.challenge.recipe.recipe.repository.RecipeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientsRepository ingredientsRepository;
    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
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
    public Recipe createRecipe(Recipe recipe) {
        return recipe;
    }
    public void updateRecipe(Long recipeId, Recipe recipe) {

    }
    public Optional<Recipe> deleteRecipeById(Long recipeId) {
        Optional<Recipe> recipeToDelete = getRecipeById(recipeId);
        ingredientsRepository.deleteAllByRecipeId(recipeId);
        recipeRepository.deleteById(recipeId);
        return recipeToDelete;
    }
}
