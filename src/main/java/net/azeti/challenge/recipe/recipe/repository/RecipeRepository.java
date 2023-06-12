package net.azeti.challenge.recipe.recipe.repository;

import net.azeti.challenge.recipe.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT r FROM Recipe r WHERE r.title like %:title%")
    List<Recipe> findAllByTitle(String title);
    @Query("SELECT r FROM Recipe r WHERE r.username like %:username%")
    List<Recipe> findAllByUsername(String username);
}
