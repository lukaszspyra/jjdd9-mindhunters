package com.infoshareacademy.domain.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FullDrinkView {

    private Long id;

    private String drinkId;

    private String drinkName;

    private CategoryView categoryView;

    private String alcoholStatus;

    private String recipe;

    private List<DrinkIngredientView> drinkIngredientViews = new ArrayList<>();

    private String image;

    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(String drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public CategoryView getCategoryView() {
        return categoryView;
    }

    public void setCategoryView(CategoryView categoryView) {
        this.categoryView = categoryView;
    }

    public String getAlcoholStatus() {
        return alcoholStatus;
    }

    public void setAlcoholStatus(String alcoholStatus) {
        this.alcoholStatus = alcoholStatus;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public List<DrinkIngredientView> getDrinkIngredientViews() {
        return drinkIngredientViews;
    }

    public void setDrinkIngredientViews(List<DrinkIngredientView> drinkIngredientViews) {
        this.drinkIngredientViews = drinkIngredientViews;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}