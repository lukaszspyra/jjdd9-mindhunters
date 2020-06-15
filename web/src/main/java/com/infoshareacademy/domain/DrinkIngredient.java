package com.infoshareacademy.domain;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "Drink.getByDrinkId",
                query = "SELECT d FROM DrinkIngredient d where d.drinkId= :drinkId"
        ),

        @NamedQuery(
                name = "Drink.getByIngredientId",
                query = "SELECT dI FROM DrinkIngredient dI where dI.ingredient= :ingredinetId"
        ),

        @NamedQuery(
                name = "Drink.getByMeasureId",
                query = "SELECT mI FROM DrinkIngredient mI where mI.measure= :measureId"
        )
})
@Entity
@Table(name = "drink_ingredient")
public class DrinkIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measure_id")
    private Measure measure;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id")
    private Drink drinkId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Drink getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Drink drinkId) {
        this.drinkId = drinkId;
    }
}