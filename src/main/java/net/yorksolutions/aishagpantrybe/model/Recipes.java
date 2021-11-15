package net.yorksolutions.aishagpantrybe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties

public class Recipes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    Long id;

    @JsonProperty
    String recipeName;

    @JsonProperty
    String recipeImage;

    @JsonProperty
    String ingredients;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "recipeSteps")
    @JsonProperty
    List<RecipeSteps> recipeSteps = new ArrayList<>();

    @JsonProperty
    String recipeCategory;

    @JsonProperty
    Boolean recipeFavorite;

    public Recipes(String recipeName, String recipeImage, String ingredients,
                   List<RecipeSteps> recipeSteps, String recipeCategory, Boolean recipeFavorite) {
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.ingredients = ingredients;
        this.recipeSteps = recipeSteps;
        this.recipeCategory = recipeCategory;
        this.recipeFavorite = recipeFavorite;
    }

    public Recipes() {

    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeSteps> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(List<RecipeSteps> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public Boolean getRecipeFavorite() {
        return recipeFavorite;
    }

    public void setRecipeFavorite(Boolean recipeFavorite) {
        this.recipeFavorite = recipeFavorite;
    }

    private class Steps {
    }
}

