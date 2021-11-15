package net.yorksolutions.aishagpantrybe.repository;

import net.yorksolutions.aishagpantrybe.model.Recipes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipesRepository extends CrudRepository<Recipes, Long> {
    List<Recipes> findByRecipeFavorite(boolean recipeFavorite);

    List<Recipes> findByRecipeCategory(String recipeCategory);
}