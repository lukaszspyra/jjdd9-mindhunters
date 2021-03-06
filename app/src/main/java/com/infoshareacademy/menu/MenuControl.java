package com.infoshareacademy.menu;

import com.infoshareacademy.domain.DrinkJson;
import com.infoshareacademy.domain.FavouritesDatabase;
import com.infoshareacademy.service.*;
import com.infoshareacademy.utilities.PropertiesUtilities;
import com.infoshareacademy.utilities.UserInput;
import com.infoshareacademy.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.infoshareacademy.domain.DrinksDatabase.getINSTANCE;
import static com.infoshareacademy.domain.FavouritesDatabase.getInstFavourites;

public class MenuControl {
    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String USER_MESSAGE = "Wrong input. Please choose number from the list.";
    public static final String FAVOURITES_JSON = "Favourites.json";

    private boolean exit = false;
    private final UserInput userInput = new UserInput();
    private final DrinkService drinkService = new DrinkService();
    private final FavouritesService favouritesService = new FavouritesService();
    private final MenuPath menuPath = new MenuPath();

    public void mainNavigation() {
        DrinkService.loadDrinkList();
        favouritesService.loadFavouritesList();
        MenuPath.reset();
        do {
            DisplayMenu.displayMainMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    MenuPath.add("BROWSE");
                    browseNavigation();
                    break;
                case 2:
                    MenuPath.add("MANAGEMENT");
                    manageNavigation();
                    break;
                case 3:
                    MenuPath.add("SETTINGS");
                    settingsNavigation();
                    break;
                case 4:
                    DisplayMenu.displayExit();
                    exit = true;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (!exit);
    }

    public void browseNavigation() {
        boolean cont = true;
        SearchService search = new SearchService();
        do {
            DisplayMenu.displayBrowseMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    MenuPath.add("ALL_DRINKS");
                    Utilities.clearScreen();
                    drinkService.printAllDrinks(getINSTANCE());
                    userInput.getUserInputAnyKey();
                    MenuPath.remove();
                    break;
                case 2:
                    MenuPath.add("FAVORITES_DRINKS");
                    Utilities.clearScreen();
                    List<DrinkJson> sortedList = favouritesService.getAllFavourites(FavouritesDatabase.getInstFavourites());
                    DrinkJson favDrink = favouritesService.chooseOneFavRecipeFromList(sortedList);
                    if (favDrink.getDrinkId() != null) {
                        DrinkService.printSingleDrink(favDrink);
                        userInput.getUserInputAnyKey();
                    }
                    MenuPath.remove();
                    break;
                case 3:
                    MenuPath.add("SEARCH_BY_DRINK");
                    Utilities.clearScreen();
                    DrinkJson foundDrinkByName = search.searchDrinkByName();
                    if (foundDrinkByName.getDrinkId() != null) {
                        Utilities.clearScreen();
                        DrinkService.printSingleDrink(foundDrinkByName);
                        if (userInput.getYesOrNo("\n\nDo you want to add this drink to favourites? <y/n>: ")) {
                            favouritesService.addToFavourites(foundDrinkByName.getDrinkId());
                            userInput.getUserInputAnyKey();
                        }
                    }
                    MenuPath.remove();
                    break;
                case 4:
                    MenuPath.add("SEARCH_BY_INGREDIENT");
                    Utilities.clearScreen();
                    DrinkJson foundDrinkByIngredient = search.searchDrinkByIngredient();
                    userInput.getUserInputAnyKey();
                    if (foundDrinkByIngredient.getDrinkId() != null) {
                        Utilities.clearScreen();
                        DrinkService.printSingleDrink(foundDrinkByIngredient);
                        if (userInput.getYesOrNo("\n\nDo you want to add this drink to favourites? <y/n>: ")) {
                            favouritesService.addToFavourites(foundDrinkByIngredient.getDrinkId());
                            userInput.getUserInputAnyKey();
                        }
                    }
                    MenuPath.remove();
                    break;
                case 5:
                    MenuPath.add("SEARCH_BY_CATEGORY");
                    Utilities.clearScreen();
                    DrinkJson foundDrinkByCategory = search.SearchByCategory();
                    if (foundDrinkByCategory.getDrinkId() != null) {
                        Utilities.clearScreen();
                        DrinkService.printSingleDrink(foundDrinkByCategory);
                        if (userInput.getYesOrNo("\n\nDo you want to add this drink to favourites? <y/n>: ")) {
                            favouritesService.addToFavourites(foundDrinkByCategory.getDrinkId());
                            userInput.getUserInputAnyKey();
                        }
                    }
                    MenuPath.remove();
                    break;
                case 6:
                    JsonWriter.writeAllToJson(getInstFavourites(), FAVOURITES_JSON);
                    cont = false;
                    MenuPath.remove();
                    break;
                case 7:
                    JsonWriter.writeAllToJson(getInstFavourites(), FAVOURITES_JSON);
                    DisplayMenu.displayExit();
                    exit = true;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (cont && (!exit));
    }

    public void manageNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displayManageMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    MenuPath.add("SAVE");
                    save();
                    MenuPath.remove();
                    break;
                case 2:
                    MenuPath.add("DELETE");
                    delete();
                    MenuPath.remove();
                    break;
                case 3:
                    MenuPath.add("UPDATE");
                    update();
                    MenuPath.remove();
                    break;
                case 4:
                    MenuPath.add("ADD_FAVOURITE");
                    String id = userInput.getUserStringInput("Type drink id to add to favourites: ");
                    favouritesService.addToFavourites(id);
                    userInput.getUserInputAnyKey();
                    MenuPath.remove();
                    break;
                case 5:
                    MenuPath.add("REMOVE_FAVORITE");
                    Utilities.clearScreen();
                    List<DrinkJson> sortedList = favouritesService.getAllFavourites(FavouritesDatabase.getInstFavourites());
                    DrinkJson favDrink = favouritesService.chooseOneFavouriteToRemoveFromList(sortedList);
                    if (favDrink.getDrinkId() != null) {
                        favouritesService.removeFromFavourites(favDrink.getDrinkId());
                        userInput.getUserInputAnyKey();
                    }
                    MenuPath.remove();
                    break;
                case 6:
                    JsonWriter.writeAllToJson(getINSTANCE(), "AllDrinks.json");
                    JsonWriter.writeAllToJson(getInstFavourites(), FAVOURITES_JSON);
                    cont = false;
                    MenuPath.remove();
                    break;
                case 7:
                    MenuPath.remove();
                    JsonWriter.writeAllToJson(getINSTANCE(), "AllDrinks.json");
                    JsonWriter.writeAllToJson(getInstFavourites(), FAVOURITES_JSON);
                    DisplayMenu.displayExit();
                    exit = true;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (cont && (!exit));
    }

    private void update() {
        boolean checkId = false;
        while (!checkId) {
            Utilities.clearScreen();
            String drinkIdToEdit = userInput.getUserStringInput("\nPlease type drink id to be edited: ");

            if (drinkService.editDrink(drinkIdToEdit)) {
                Utilities.clearScreen();
                STDOUT.info("Drink update complete. Press any key to continue: ");
                userInput.getUserInputAnyKey();
                checkId = true;
            } else {
                Utilities.clearScreen();
                STDOUT.info("\nDrink ID not found. ");
                if (!userInput.getYesOrNo("Do you want to try again? <y/n>")) {
                    STDOUT.info("\nDrink edit unsuccessful - drink not found. Press any key to continue: ");
                    userInput.getUserInputAnyKey();
                    checkId = true;
                }
            }
        }
    }

    private void delete() {
        boolean checkId = false;
        while (!checkId) {
            Utilities.clearScreen();
            String drinkIdToDelete = userInput.getUserStringInput("\nPlease type drink id to be removed: ");

            if (drinkService.removeDrink(drinkIdToDelete)) {
                Utilities.clearScreen();
                STDOUT.info("Drink removal complete. Press any key to continue: ");
                userInput.getUserInputAnyKey();
                checkId = true;
            } else {
                Utilities.clearScreen();
                STDOUT.info("\nDrink ID not found. ");
                if (!userInput.getYesOrNo("Do you want to try again? <y/n>")) {
                    STDOUT.info("\nDrink removal unsuccessful - drink not found. Press any key to continue: ");
                    userInput.getUserInputAnyKey();
                    checkId = true;
                }
            }
        }
    }

    private void save() {
        drinkService.createDrink();
        Utilities.clearScreen();
        STDOUT.info("\nDrink added to database. Press any key to continue: ");
        userInput.getUserInputAnyKey();
    }

    public void settingsNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displaySettingsMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    MenuPath.add("SORTING_ORDER");
                    settingsOrderNavigation();
                    break;
                case 2:
                    MenuPath.add("DATE_FORMAT");
                    settingsDateFormatNavigation();
                    break;
                case 3:
                    cont = false;
                    MenuPath.remove();
                    break;
                case 4:
                    DisplayMenu.displayExit();
                    exit = true;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (cont && (!exit));
    }

    public void settingsOrderNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displaySettingsOrderMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    (new PropertiesUtilities()).setProperties("orderby", "asc");
                    break;
                case 2:
                    (new PropertiesUtilities()).setProperties("orderby", "desc");
                    break;
                case 3:
                    cont = false;
                    MenuPath.remove();
                    break;
                case 4:
                    DisplayMenu.displayExit();
                    exit = true;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (cont && (!exit));
    }

    public void settingsDateFormatNavigation() {
        boolean cont = true;
        do {
            DisplayMenu.displaySettingsDateFormatMenu();
            switch (userInput.getUserNumericInput()) {
                case 1:
                    (new PropertiesUtilities()).setProperties("date.format", "YYYY-MM-dd HH:mm");
                    break;
                case 2:
                    (new PropertiesUtilities()).setProperties("date.format", "dd-MM-YYYY HH:mm");
                    break;
                case 3:
                    cont = false;
                    MenuPath.remove();
                    break;
                case 4:
                    DisplayMenu.displayExit();
                    exit = true;
                    break;
                default:
                    STDOUT.info(USER_MESSAGE);
                    Utilities.freezeConsole();
                    break;
            }
        } while (cont && (!exit));
    }
}
