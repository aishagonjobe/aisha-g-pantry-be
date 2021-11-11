package net.yorksolutions.aishagpantrybe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.yorksolutions.aishagpantrybe.model.Recipes;
import net.yorksolutions.aishagpantrybe.repository.RecipesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/recipe")
@RestController
public class RecipesController {
    @Autowired
    RecipesRepository recipesRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/get-recipes")
    String requestRecipe() throws JsonProcessingException {
        return objectMapper.writeValueAsString(recipesRepository.findAll());
    }

    @PostMapping("/create-recipe")
    String createRecipe(@RequestBody String body) {
        try {
            Recipes recipe = objectMapper.readValue(body, Recipes.class);
            recipesRepository.save(recipe);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
        return "success";
    }

    @GetMapping("/get-recipe-item/id")
    String requestRecipeItem(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Recipes> recipesList = (List<Recipes>) recipesRepository.findAll();
        recipesList = recipesList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(recipesList);
    }

    @PutMapping("/update-recipe/id")
    String updateRecipe(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Recipes> recipesList = (List<Recipes>) recipesRepository.findAll();
        recipesList = recipesList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(recipesList);
    }

    @DeleteMapping("/delete-recipe/id")
    String deleteRecipe(@RequestParam("rows") String rows) throws JsonProcessingException {
        List<Recipes> recipesList = (List<Recipes>) recipesRepository.findAll();
        recipesList = recipesList.stream().limit(Long.parseLong(rows)).collect(Collectors.toList());
        return objectMapper.writeValueAsString(recipesList);
    }

    @GetMapping("/recipe-category")
    String requestRecipeCategory() throws JsonProcessingException {
        return objectMapper.writeValueAsString(recipesRepository.findAll());
    }
}
