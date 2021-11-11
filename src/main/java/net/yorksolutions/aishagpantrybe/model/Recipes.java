package net.yorksolutions.aishagpantrybe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
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

    @JsonProperty
    String recipeSteps;

    @JsonProperty
    String recipeCategory;

    @JsonProperty
    Boolean favorite;

    public Recipes(String recipeName, String recipeImage, String ingredients, String recipeSteps, String recipeCategory, Boolean favorite) {
        this.recipeName = recipeName;
        this.recipeImage = recipeImage;
        this.ingredients = ingredients;
        this.recipeSteps = recipeSteps;
        this.recipeCategory = recipeCategory;
        this.favorite = favorite;
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

    public String getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(String recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }
}

