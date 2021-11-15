package net.yorksolutions.aishagpantrybe.model;

import javax.persistence.*;

@Table(name = "recipe_steps")
@Entity
public class RecipeSteps {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recipes_id")
    private RecipeSteps recipeSteps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecipeSteps getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(Recipes recipes) {
        this.recipeSteps = recipeSteps;
    }
}