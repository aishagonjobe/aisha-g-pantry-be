package net.yorksolutions.aishagpantrybe.controller;

import net.yorksolutions.aishagpantrybe.model.Recipes;
import net.yorksolutions.aishagpantrybe.repository.RecipesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/recipes")
public class RecipesController {

    @Autowired
    RecipesRepository recipesRepository;

    @GetMapping("/get-recipes")
    public ResponseEntity<List<Recipes>> requestRecipes() {
        try {
            List<Recipes> recipesList = new ArrayList<Recipes>();
            recipesRepository.findAll().forEach(recipesList::add);

            if (recipesList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(recipesList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<Recipes>> findByRecipeFavorite() {
        try {
            List<Recipes> recipe = recipesRepository.findByRecipeFavorite(true);

            if (recipe.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(recipe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/get-recipe")
    public ResponseEntity<Recipes> requestRecipe(@RequestParam("id") Long id) {
        Optional<Recipes> recipeData = recipesRepository.findById(id);

        if (recipeData.isPresent()) {
            return new ResponseEntity<>(recipeData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-recipe")
    public ResponseEntity<Recipes> createRecipe(@RequestBody Recipes recipes) {
        try {
            Recipes recipe = recipesRepository
                    .save(new Recipes(
                            recipes.getRecipeName(),
                            recipes.getRecipeImage(),
                            recipes.getIngredients(),
                            recipes.getRecipeSteps(),
                            recipes.getRecipeCategory(),
                            false
                    ));
            return new ResponseEntity<>(recipe, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update-recipe")
    public ResponseEntity<Recipes> updateRecipe(@RequestParam("id") Long id, @RequestBody Recipes recipes) {
        Optional<Recipes> recipesData = recipesRepository.findById(id);

        if (recipesData.isPresent()) {
            Recipes recipe = recipesData.get();
            recipes.setRecipeName(recipe.getRecipeName());
            recipes.setRecipeImage(recipes.getRecipeImage());
            recipes.setIngredients(recipes.getIngredients());
            recipes.setRecipeSteps(recipes.getRecipeSteps());
            recipes.setRecipeFavorite(recipes.getRecipeFavorite());
            recipes.setRecipeCategory(recipes.getRecipeCategory());
            return new ResponseEntity<>(recipesRepository.save(recipe), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-recipe")
    public ResponseEntity<?> deleteRecipe(@RequestParam("id") Long id) {
        try {
            recipesRepository.deleteById(id);
            return new ResponseEntity<String>("Deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>("Internal error.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

