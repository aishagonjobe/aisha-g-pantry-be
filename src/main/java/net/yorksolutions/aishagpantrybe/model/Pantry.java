package net.yorksolutions.aishagpantrybe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties

public class Pantry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    Long id;

    @JsonProperty
    String foodName;

    @JsonProperty
    String foodImage;

    @JsonProperty
    int weight;

    @JsonProperty
    int calories;

    @JsonProperty
    Boolean available;

    @JsonProperty
    Boolean favorite;

    @JsonProperty
    String foodCategory;

    public Pantry() {

    }

    public Pantry(String foodName, String foodImage, int weight, int calories,
                  boolean available, boolean favorite, String foodCategory) {
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.weight = weight;
        this.calories = calories;
        this.available = available;
        this.favorite = favorite;
        this.foodCategory = foodCategory;
    }

    public Long getId() {
        return id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

}

