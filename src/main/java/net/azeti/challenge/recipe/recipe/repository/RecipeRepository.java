package net.azeti.challenge.recipe.recipe.repository;

import net.azeti.challenge.recipe.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    @Query("SELECT r FROM Recipe r WHERE r.title LIKE %:title%")
    List<Recipe> findAllByTitle(String title);
    @Query("SELECT r FROM Recipe r WHERE r.username LIKE %:username%")
    List<Recipe> findAllByUsername(String username);
    @Query("SELECT r FROM Recipe r WHERE lower(r.instructions) NOT LIKE '%bake%' ")
    List<Recipe> findAllNonBakedRecipes();
    @Query("SELECT r FROM Recipe r WHERE NOT EXISTS (SELECT i FROM r.ingredients i WHERE i.type LIKE '%frozen%') ")
    List<Recipe> findAllWithNonFrozenIngredients();
    @Query("SELECT r FROM Recipe r WHERE r.username = (SELECT u.username FROM User u WHERE u.id = :userId)")
    List<Recipe> findAllByUserId(Long userId);
}
