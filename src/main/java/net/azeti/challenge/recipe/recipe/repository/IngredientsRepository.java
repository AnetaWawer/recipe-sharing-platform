package net.azeti.challenge.recipe.recipe.repository;

import net.azeti.challenge.recipe.recipe.model.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    void deleteAllByRecipeId(Long recipeId);
    List<Ingredients> findAllByRecipeId(Long recipeId);
}
