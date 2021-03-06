package com.infoshareacademy.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(
                name = "Drink.findByIngredients",
                query = "SELECT d FROM Drink d JOIN d.drinkIngredients di WHERE di.ingredient IN :ingredients and d.isApproved = true GROUP BY " +
                        "d ORDER BY COUNT (di.ingredient) DESC "),
        @NamedQuery(
                name = "Drink.countByIngredients",
                query = "SELECT COUNT(DISTINCT d.id) FROM Drink d JOIN d.drinkIngredients di WHERE di.ingredient IN " +
                        ":ingredients and d.isApproved = true"),
        @NamedQuery(
                name = "Drink.findDrinkByPartialName",
                query = "SELECT d FROM Drink d WHERE LOWER( d.drinkName) LIKE LOWER(:partialDrinkName) and d.isApproved = true"),

        @NamedQuery(
                name = "Drink.countDrinksByPartialName",
                query = "SELECT COUNT(d) FROM Drink d WHERE LOWER( d.drinkName) LIKE LOWER(:partialDrinkName) and d.isApproved = true") ,

        @NamedQuery(
                name = "Drink.findAll",
                query = "SELECT d FROM Drink d where d.isApproved = true"
        ),
        @NamedQuery(
                name = "Drink.countFindAll",
                query = "SELECT count (d) FROM Drink d where d.isApproved = true"
        ),
        @NamedQuery(
                name = "Drink.findDrinksByCategories",
                query = "select d from Drink d where d.category.id in (:category) and d.isApproved = true"
        ),
        @NamedQuery(
                name = "Drink.CountDrinksByCategories",
                query = "select count (d) from Drink d where d.category.id in (:category) and d.isApproved = true"
        ),
        @NamedQuery(
                name = "Drink.findDrinksByAlcoholStatus",
                query = "select d from Drink d where d.alcoholStatus in (:alcoholStatus) and d.isApproved = true"
        ),
        @NamedQuery(
                name = "Drink.countDrinksByAlcoholStatus",
                query = "select count (d) from Drink d where d.alcoholStatus in (:alcoholStatus) and d.isApproved = true"
        ),
        @NamedQuery(
                name = "Drink.findByCategoriesAndAlcoholStatus",
                query = "select d from Drink d  where d.alcoholStatus  in (:alcoholStatus) and d.category.id in (:category) and d.isApproved = true"
        ),
        @NamedQuery(
                name = "Drink.countDrinksByCategoriesAndAlcoholStatus",
                query = "select count (d) from Drink d where d.alcoholStatus  in (:alcoholStatus) and d.category.id in (:category) and d.isApproved = true"
        ),
        @NamedQuery(
                name = "Drink.findAllByCategories",
                query = "select d from Drink d where d.category.name in :category  and d.isApproved = true"
        ),

        @NamedQuery(
                name = "Drinks.getDrinksInAllCategories",
                query = "SELECT c.name, COUNT(d.drinkName) as quantity FROM Drink d JOIN d.category c  WHERE d.isApproved = true GROUP BY c" +
                        ".name ORDER BY c.name ASC"),
        @NamedQuery(
                name = "Drinks.getAllUsers",
                query = "SELECT d.users FROM Drink d where d.id = :id AND d.isApproved = true"),

        @NamedQuery(
                name = "Drink.getDrinksToApprove",
                query = "SELECT d FROM Drink d where d.isApproved = false "),

        @NamedQuery(
                name = "Drink.deleteIngredientsByDrink",
                query = "DELETE FROM DrinkIngredient di where di.drinkId.id = :drinkId ")

})

@Entity
@Table(name = "drink")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id")
    private String drinkId;

    @Column(name = "name")
    @NotNull
    private String drinkName;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "alcohol_status")
    @NotNull
    private String alcoholStatus;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String recipe;

    @OneToMany(cascade = {CascadeType.ALL, CascadeType.MERGE}, mappedBy = "drinkId", fetch = FetchType.LAZY)
    private List<DrinkIngredient> drinkIngredients = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "drinks")
    private List<User> users = new ArrayList<>();

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "drink", fetch = FetchType.LAZY)
    private List<Statistics> statisticsList = new ArrayList<>();

    private  Long parentId;

    private String manageAction;

    private String image;

    private LocalDateTime date;

    private boolean isApproved;

    private String confirmUserEmail;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public List<DrinkIngredient> getDrinkIngredients() {
        return drinkIngredients;
    }

    public void setDrinkIngredients(List<DrinkIngredient> drinkIngredients) {
        this.drinkIngredients = drinkIngredients;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Statistics> getStatisticsList() {
        return statisticsList;
    }

    public void setStatisticsList(List<Statistics> statisticsList) {
        this.statisticsList = statisticsList;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getManageAction() {
        return manageAction;
    }

    public void setManageAction(String manageAction) {
        this.manageAction = manageAction;
    }

    public String getConfirmUserEmail() {
        return confirmUserEmail;
    }

    public void setConfirmUserEmail(String confirmUserEmail) {
        this.confirmUserEmail = confirmUserEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Drink))
            return false;
        Drink drink = (Drink) o;
        return id.equals(drink.id) &&
                drinkId.equals(drink.drinkId) &&
                drinkName.equals(drink.drinkName) &&
                Objects.equals(category, drink.category) &&
                Objects.equals(alcoholStatus, drink.alcoholStatus) &&
                Objects.equals(recipe, drink.recipe) &&
                Objects.equals(drinkIngredients, drink.drinkIngredients) &&
                Objects.equals(image, drink.image) &&
                Objects.equals(date, drink.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, drinkId, drinkName, category, alcoholStatus, recipe, image, date);
    }
}
