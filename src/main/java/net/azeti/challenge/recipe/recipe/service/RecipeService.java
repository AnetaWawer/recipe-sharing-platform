package net.azeti.challenge.recipe.recipe.service;

import lombok.RequiredArgsConstructor;
import net.azeti.challenge.recipe.recipe.model.Recipe;
import net.azeti.challenge.recipe.recipe.repository.RecipeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    public List<Recipe> getAllRecipes(){
        return recipeRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
    }
    public Optional<Recipe> getRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId);
    }
}
