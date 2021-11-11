package net.yorksolutions.aishagpantrybe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties

public class Groceries {
    @Id
    @JsonProperty
    Long id;

    @JsonProperty
    String foodName;

    @JsonProperty
    String foodImage;

    @JsonProperty
    int weight;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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
}