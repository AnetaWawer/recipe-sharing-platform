package net.azeti.challenge.recipe.recipe.service;

import net.azeti.challenge.recipe.recipe.model.Ingredients;
import net.azeti.challenge.recipe.recipe.model.Recipe;
import net.azeti.challenge.recipe.recipe.model.Units;
import net.azeti.challenge.recipe.user.model.User;
import net.azeti.challenge.recipe.recipe.repository.IngredientsRepository;
import net.azeti.challenge.recipe.recipe.repository.RecipeRepository;
import net.azeti.challenge.recipe.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


import static org.mockito.Mockito.*;
@SpringBootTest
public class RecipeServiceTest {
    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    IngredientsRepository ingredientsRepository;
    @Mock
    UserRepository userRepository;
    private List<Recipe> exampleRecipes;
    private Recipe exampleRecipe1 = new Recipe();
    private Recipe exampleRecipe2 = new Recipe();
    @InjectMocks
    private RecipeService recipeService;
    @BeforeEach
    void setup(){
        exampleRecipes = new ArrayList<>();
        Ingredients ingredient1 = new Ingredients(1L, exampleRecipe1, 200, Units.G, "cheese" );
        Ingredients ingredient2 = new Ingredients(2L, exampleRecipe1, 250, Units.ML, "sauce" );
        exampleRecipe1 = new Recipe(1L,"Pizza","John","Pizza Margherita",List.of(ingredient1, ingredient2), "Please bake pizza for about 14 minutes", 4);
        Ingredients ingredient3 = new Ingredients(3L, exampleRecipe2, 200, Units.G, "frozen banana" );
        Ingredients ingredient4 = new Ingredients(4L, exampleRecipe2, 250, Units.ML, "milk" );
        exampleRecipe2 = new Recipe(2L, "Banana shake", "Kate", "Banana shake", List.of(ingredient3, ingredient4), "Blend all ingredients together", 2);
        exampleRecipes.add(exampleRecipe1);
        exampleRecipes.add(exampleRecipe2);
    }
    @Test
    void shouldReturnAllRecipes(){
        when(recipeRepository.findAll()).thenReturn(exampleRecipes);
        List<Recipe> actualRecipes = recipeService.getAllRecipes();
        assertEquals(2, actualRecipes.size());
        assertEquals(exampleRecipes, actualRecipes);
    }
    @Test
    void shouldReturnRecipeById(){
        when(recipeRepository.findById(any(Long.class))).thenReturn(Optional.of(exampleRecipe1));
        Recipe actualRecipe = recipeService.getRecipeById(1L).orElseThrow();
        assertEquals(exampleRecipe1, actualRecipe);
    }
    @Test
    void shouldReturnRecipesByUserId(){
        when(recipeRepository.findAllByUserId(any(Long.class))).thenReturn(exampleRecipes);
        List<Recipe> actualRecipes = recipeService.getRecipesByUserId(1L);
        assertEquals(2, actualRecipes.size());
        assertEquals(exampleRecipes, actualRecipes);
    }
    @Test
    void shouldReturnRecipeByUsername(){
        String searchDetails = "John";
        List<Recipe> filteredRecipes = exampleRecipes.stream()
                .filter(recipe -> recipe.getUsername().equals(searchDetails))
                .collect(Collectors.toList());
        when(recipeRepository.findAllByUsername(any(String.class))).thenReturn(filteredRecipes);
        List<Recipe> actualRecipes = recipeService.getRecipesByUsername(searchDetails);
        assertEquals(1, actualRecipes.size());
        assertEquals(filteredRecipes, actualRecipes);
    }
    @Test
    void shouldReturnRecipeByRecipeTitle(){
        String searchDetails = "Pizza";
        List<Recipe> filteredRecipes = exampleRecipes.stream()
                .filter(recipe -> recipe.getTitle().equals(searchDetails))
                .collect(Collectors.toList());
        when(recipeRepository.findAllByTitle(any(String.class))).thenReturn(filteredRecipes);
        List<Recipe> actualRecipes = recipeService.getRecipesByTitle(searchDetails);
        assertEquals(1, actualRecipes.size());
        assertEquals(filteredRecipes, actualRecipes);
    }
    @Test
    void shouldReturnRecipesThatDoNotRequireBaking(){
        List<Recipe> filteredRecipes = exampleRecipes.stream()
                .filter(recipe -> !recipe.getInstructions().contains("bake"))
                .collect(Collectors.toList());
        when(recipeRepository.findAllNonBakedRecipes()).thenReturn(filteredRecipes);
        List<Recipe> actualRecipes = recipeService.getRecipesNotRequiringBaking();
        assertEquals(1, actualRecipes.size());
        assertEquals(filteredRecipes, actualRecipes);
    }
    @Test
    void shouldReturnRecipesThatDoNotContainFrozenIngredients(){
        List<Recipe> filteredRecipes = exampleRecipes.stream()
                 .filter(recipe -> recipe.getIngredients().stream().noneMatch(ingredient -> ingredient.getType().contains("frozen")))
                .collect(Collectors.toList());
        when(recipeRepository.findAllWithNonFrozenIngredients()).thenReturn(filteredRecipes);
        List<Recipe> actualRecipes = recipeService.getRecipesNotUsingFrozenIngredients();
        assertEquals(1, actualRecipes.size());
        assertEquals(filteredRecipes, actualRecipes);
    }
    @Test
    void shouldDeleteRecipe(){
        recipeService.deleteRecipeById(1L);
        verify(recipeRepository).deleteById(any());
    }
    @Test
    void createRecipe_shouldSaveRecipe(){
        User user = new User();
        user.setUsername("John");
        when(recipeRepository.save(any(Recipe.class))).thenReturn(exampleRecipe1);
        Recipe actualRecipe = recipeService.createRecipe(exampleRecipe1, user);
        assertEquals(exampleRecipe1.getTitle(), actualRecipe.getTitle());
        assertEquals(exampleRecipe1.getDescription(), actualRecipe.getDescription());
        assertEquals(exampleRecipe1.getInstructions(), actualRecipe.getInstructions());
        assertEquals(exampleRecipe1.getServings(), actualRecipe.getServings());
        assertEquals(exampleRecipe1.getUsername(), actualRecipe.getUsername());
        assertEquals(exampleRecipe1.getIngredients(), actualRecipe.getIngredients());
    }
}
