package net.azeti.challenge.recipe.recipe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IngredientsTest {
    @Mock
    private Ingredients ingredients;
    @BeforeEach
    public void setUp(){
        ingredients = new Ingredients();
    }
    @Test
    public void testSetter_shouldSetRecipe() throws NoSuchFieldException, IllegalAccessException {
        Recipe recipe = new Recipe();
        ingredients.setRecipe(recipe);
        final Field actualRecipe = ingredients.getClass().getDeclaredField("recipe");
        actualRecipe.setAccessible(true);
        assertEquals(recipe,actualRecipe.get(ingredients));
    }
    @Test
    public void testGetter_shouldGetRecipe() throws NoSuchFieldException, IllegalAccessException {
        final Field field = ingredients.getClass().getDeclaredField("recipe");
        field.setAccessible(true);
        Recipe actualRecipe = new Recipe();
        field.set(ingredients,actualRecipe);
        final Recipe recipe = ingredients.getRecipe();
        assertEquals(recipe,actualRecipe);
    }
    @Test
    public void testSetter_shouldSetValue() throws NoSuchFieldException, IllegalAccessException {
        double value = 2.5;
        ingredients.setValue(value);
        final Field actualValue = ingredients.getClass().getDeclaredField("value");
        actualValue.setAccessible(true);
        assertEquals(value,actualValue.get(ingredients));
    }
    @Test
    public void testGetter_shouldGetValue() throws NoSuchFieldException, IllegalAccessException {
        final Field field = ingredients.getClass().getDeclaredField("value");
        field.setAccessible(true);
        double actualValue = 2.5;
        field.set(ingredients,actualValue);
        final double value = ingredients.getValue();
        assertEquals(value,actualValue);
    }
    @Test
    public void testSetter_shouldSetUnit() throws NoSuchFieldException, IllegalAccessException {
        Units unit =Units.ML;
        ingredients.setUnit(unit);
        final Field actualUnit = ingredients.getClass().getDeclaredField("unit");
        actualUnit.setAccessible(true);
        assertEquals(unit,actualUnit.get(ingredients));
    }
    @Test
    public void testGetter_shouldGetUnit() throws NoSuchFieldException, IllegalAccessException {
        final Field field = ingredients.getClass().getDeclaredField("unit");
        field.setAccessible(true);
        Units actualUnit =Units.ML;
        field.set(ingredients,actualUnit);
        final Units unit = ingredients.getUnit();
        assertEquals(unit,actualUnit);
    }
    @Test
    public void testSetter_shouldSetType() throws NoSuchFieldException, IllegalAccessException {
        String type = "Milk";
        ingredients.setType(type);
        final Field actualUnit = ingredients.getClass().getDeclaredField("type");
        actualUnit.setAccessible(true);
        assertEquals(type,actualUnit.get(ingredients));
    }
    @Test
    public void testGetter_shouldGetType() throws NoSuchFieldException, IllegalAccessException {
        final Field field = ingredients.getClass().getDeclaredField("type");
        field.setAccessible(true);
        String actualType = "Milk";
        field.set(ingredients,actualType);
        final String type = ingredients.getType();
        assertEquals(type,actualType);
    }
    @Test
    public void testSetter_shouldSetId() throws NoSuchFieldException, IllegalAccessException {
        Long id = 1L;
        ingredients.setId(id);
        final Field actualId = ingredients.getClass().getDeclaredField("id");
        actualId.setAccessible(true);
        assertEquals(id,actualId.get(ingredients));
    }
    @Test
    public void testGetter_shouldGetId() throws NoSuchFieldException, IllegalAccessException {
        final Field field = ingredients.getClass().getDeclaredField("id");
        field.setAccessible(true);
        Long actualId = 1L;
        field.set(ingredients,actualId);
        final Long id = ingredients.getId();
        assertEquals(id,actualId);
    }

}