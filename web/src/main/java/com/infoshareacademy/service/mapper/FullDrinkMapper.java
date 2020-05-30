package com.infoshareacademy.service.mapper;

import com.infoshareacademy.domain.Drink;
import com.infoshareacademy.domain.dto.FullDrinkView;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class FullDrinkMapper {

    @Inject
    private DrinkIgredientMapper drinkIgredientMapper;

    @Inject
    private CategoryMapper categoryMapper;

    public FullDrinkView toView(Drink drink) {
        FullDrinkView fullDrinkView = new FullDrinkView();
        fullDrinkView.setId(drink.getId());
        fullDrinkView.setDrinkId(drink.getDrinkId());
        fullDrinkView.setDrinkName(drink.getDrinkName());
        fullDrinkView.setCategoryView(categoryMapper.toView(drink.getCategory()));
        fullDrinkView.setAlcoholStatus(drink.getAlcoholStatus());
        fullDrinkView.setRecipe(drink.getRecipe());
        fullDrinkView.setDrinkIngredientViews(drink.getDrinkIngredients().stream()
                .map(drinkIgredientMapper::toView)
                .collect(Collectors.toList()));
        fullDrinkView.setImage(drink.getImage());
        fullDrinkView.setDate(drink.getDate());
        return fullDrinkView;
    }

    public List<FullDrinkView> toView(List<Drink> drinks) {
        List<FullDrinkView> fullDrinkViews = new ArrayList<>();
        for (Drink drink : drinks) {
            fullDrinkViews.add(toView(drink));
        }
        return fullDrinkViews;
    }
}