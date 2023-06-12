package net.azeti.challenge.recipe.recipe.repository;

import net.azeti.challenge.recipe.recipe.model.Recipe;

import java.util.List;

public interface RecipeSearch {

    List<Recipe> recipesByUsername(String usernameValue);

    List<Recipe> recipesByTitle(String titleValue);
}
