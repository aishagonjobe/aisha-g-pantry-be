package net.yorksolutions.aishagpantrybe.repository;

import net.yorksolutions.aishagpantrybe.model.Recipes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipesRepository extends CrudRepository<Recipes, Long> {
}