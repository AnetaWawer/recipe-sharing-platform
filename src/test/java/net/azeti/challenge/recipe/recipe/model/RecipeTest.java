package net.azeti.challenge.recipe.recipe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RecipeTest {
    @Mock
    private Recipe recipe;
    @BeforeEach
    public void setUp(){
        recipe = new Recipe();
    }
    @Test
    public void testSetter_shouldSetTitle() throws NoSuchFieldException, IllegalAccessException {
        String title = "Spaghetti Bolognese";
        recipe.setTitle(title);
        final Field actualTitle = recipe.getClass().getDeclaredField("title");
        actualTitle.setAccessible(true);
        assertEquals(title,actualTitle.get(recipe));
    }
    @Test
    public void testGetter_shouldGetTitle() throws NoSuchFieldException, IllegalAccessException {
        final Field field = recipe.getClass().getDeclaredField("title");
        field.setAccessible(true);
        String actualTitle = "Spaghetti Bolognese";
        field.set(recipe,actualTitle);
        final String title = recipe.getTitle();
        assertEquals(title,actualTitle);
    }
    @Test
    public void testSetter_shouldSetDescription() throws NoSuchFieldException, IllegalAccessException {
        String description = "This is a tasty recipe.";
        recipe.setDescription(description);
        final Field actualTitle = recipe.getClass().getDeclaredField("description");
        actualTitle.setAccessible(true);
        assertEquals(description,actualTitle.get(recipe));
    }
    @Test
    public void testGetter_shouldGetDescription() throws NoSuchFieldException, IllegalAccessException {
        final Field field = recipe.getClass().getDeclaredField("description");
        field.setAccessible(true);
        String actualDescription = "This is a tasty recipe.";
        field.set(recipe,actualDescription);
        final String description = recipe.getDescription();
        assertEquals(description,actualDescription);
    }
    @Test
    public void testSetter_shouldSetInstructions() throws NoSuchFieldException, IllegalAccessException {
        String instructions = "Bake for 20min";
        recipe.setInstructions(instructions);
        final Field actualInstructions = recipe.getClass().getDeclaredField("instructions");
        actualInstructions.setAccessible(true);
        assertEquals(instructions,actualInstructions.get(recipe));
    }
    @Test
    public void testGetter_shouldGetInstructions() throws NoSuchFieldException, IllegalAccessException {
        final Field field = recipe.getClass().getDeclaredField("instructions");
        field.setAccessible(true);
        String actualInstructions = "Bake for 20min";
        field.set(recipe,actualInstructions);
        final String instructions = recipe.getInstructions();
        assertEquals(instructions,actualInstructions);
    }
    @Test
    public void testSetter_shouldSetServings() throws NoSuchFieldException, IllegalAccessException {
        int servings = 2;
        recipe.setServings(servings);
        final Field actualServings = recipe.getClass().getDeclaredField("servings");
        actualServings.setAccessible(true);
        assertEquals(servings,actualServings.get(recipe));
    }
    @Test
    public void testGetter_shouldGetServings() throws NoSuchFieldException, IllegalAccessException {
        final Field field = recipe.getClass().getDeclaredField("servings");
        field.setAccessible(true);
        int actualServings = 4;
        field.set(recipe,actualServings);
        final int instructions = recipe.getServings();
        assertEquals(instructions,actualServings);
    }
    @Test
    public void testSetter_shouldSetIngredients() throws NoSuchFieldException, IllegalAccessException {
        List<Ingredients> ingredients = List.of(new Ingredients(), new Ingredients());
        recipe.setIngredients(ingredients);
        final Field actualServings = recipe.getClass().getDeclaredField("ingredients");
        actualServings.setAccessible(true);
        assertEquals(2, ingredients.size());
        assertEquals(ingredients,actualServings.get(recipe));
    }
    @Test
    public void testGetter_shouldGetIngredients() throws NoSuchFieldException, IllegalAccessException {
        final Field field = recipe.getClass().getDeclaredField("ingredients");
        field.setAccessible(true);
        List<Ingredients> actualIngredients = new ArrayList<>();
        actualIngredients.add(new Ingredients());
        field.set(recipe,actualIngredients);
        final List<Ingredients> ingredients = recipe.getIngredients();
        assertEquals(1,actualIngredients.size());
        assertEquals(ingredients,actualIngredients);
    }
    @Test
    public void testSetter_shouldSetUsername() throws NoSuchFieldException, IllegalAccessException {
        String username = "John";
        recipe.setUsername(username);
        final Field actualUsername = recipe.getClass().getDeclaredField("username");
        actualUsername.setAccessible(true);
        assertEquals(username,actualUsername.get(recipe));
    }
    @Test
    public void testGetter_shouldGetUsername() throws NoSuchFieldException, IllegalAccessException {
        final Field field = recipe.getClass().getDeclaredField("username");
        field.setAccessible(true);
        String actualUsername = "John";
        field.set(recipe,actualUsername);
        final String username = recipe.getUsername();
        assertEquals(username,actualUsername);
    }
    @Test
    public void testSetter_shouldSetId() throws NoSuchFieldException, IllegalAccessException {
        Long id = 1L;
        recipe.setId(id);
        final Field actualId = recipe.getClass().getDeclaredField("id");
        actualId.setAccessible(true);
        assertEquals(id,actualId.get(recipe));
    }
    @Test
    public void testGetter_shouldGetId() throws NoSuchFieldException, IllegalAccessException {
        final Field field = recipe.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Long actualId = 1L;
        field.set(recipe,actualId);
        final Long id = recipe.getId();
        assertEquals(id,actualId);
    }
}
