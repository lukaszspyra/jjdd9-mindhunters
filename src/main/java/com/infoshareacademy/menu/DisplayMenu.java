package com.infoshareacademy.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DisplayMenu {

    private static final Logger STDOUT = LoggerFactory.getLogger("CONSOLE_OUT");
    private static final String END_LINE = "\n -------------------------------------------- ";

    private DisplayMenu() {
    }

    public static void displayMainMenu() {
        clearScreen();
        STDOUT.info("\n --------------- Main Menu ------------------ ");
        STDOUT.info("\n|  [1] to browse drinks recipes              |" +
                "\n|  [2] to manage recipes                     |" +
                "\n|  [3] to change settings                    |" +
                "\n|  [4] to EXIT                               |");
        STDOUT.info(END_LINE);
    }

    public static void displayBrowseMenu() {
        clearScreen();
        STDOUT.info("\n ------------ Drink Browse Menu ------------- ");
        STDOUT.info("\n|  [1] to display all drinks list            |" +
                "\n|  [2] to search drink by name               |" +
                "\n|  [3] to search drink by ingredient         |" +
                "\n|  [4] to search drink by category           |" +
                "\n|  [5] to return to previous menu            |" +
                "\n|  [6] to EXIT                               |");
        STDOUT.info(END_LINE);
    }

    public static void displayManageMenu() {
        clearScreen();
        STDOUT.info("\n ---------- Drink Management Menu ----------- ");
        STDOUT.info("\n|  [1] to add drink                          |" +
                "\n|  [2] to delete drink                       |" +
                "\n|  [3] to add drink to favourites            |" +
                "\n|  [4] to remove drink from favourites       |" +
                "\n|  [5] to return to previous menu            |" +
                "\n|  [6] to EXIT                               |");
        STDOUT.info(END_LINE);
    }

    public static void displaySettingsMenu() {
        clearScreen();
        STDOUT.info("\n ---------------- Settings ------------------- ");
        STDOUT.info("\n|  [1] to load/change configuration           |" +
                "\n|  [2] to change drinks sorting order         |" +
                "\n|  [3] to set recipe data modification format |" +
                "\n|  [4] to return to previous menu             |" +
                "\n|  [5] to EXIT                                |");
        STDOUT.info(END_LINE);
    }

    public static void displayExit() {
        clearScreen();
        STDOUT.info("\n ------------------ QUIT -------------------- ");
        STDOUT.info("\n|  Hope to see you again soon.               |" +
                "\n|                                            |" +
                "\n|  Enjoy responsibly!                        |");
        STDOUT.info(END_LINE);
        STDOUT.info("\n");
    }

    public static void logo() {
        clearScreen();
        STDOUT.info("\n ------------- Welcome in Mindhunters World of Goodies ------------------ ");
        STDOUT.info("\n|                                                                        |" +
                "\n|                                                                        |" +
                "\n|                                                     .''''.             |" +
                "\n|                                                    /,.--. )            |" +
                "\n|                               .'``.        __   __((\\- -(\\)            |" +
                "\n|                      _______.'     \\_.-''''  ``'  /)) - . |            |" +
                "\n|     __....::::::::::'''''''/    .   \\ ''''''::::::(/ `-'`.)            |" +
                "\n|  .:'::.  .  o ~ .  ~  o ~ /    /     '.o ~ . _.....--- `   \\           |" +
                "\n|   ':. ':::::.___.____,___/   ,'_\\     \\ _.-'___..___..._,' )           |" +
                "\n|     ':.  o~  `::::::::::::::::::::::::::::::::::::::::'  (\\ |          |" +
                "\n|        `':.  o ~  o   ..   o  ,  ~  \\ . o~   -.  ~   .'.:'\\'(          |" +
                "\n|            ':.  ,..   o  ~   o  . ,  'o.    ~ o   ~ o'.:' \\(/          |" +
                "\n|               '.   o   ~   .    ~    o ~ ',o :  :  .' .'('\\/|          |" +
                "\n|                 '-._    ~    o  , o  ,  .  ~._ _.o_.-' \\/ ) (          |" +
                "\n|                     '- .._  .    ~    ~      _.. -'                    |" +
                "\n|                           ''' - .,.,. - '''                            |" +
                "\n|                                (:' .:)                                 |" +
                "\n|                                 :| '|                                  |" +
                "\n|                                 |. ||                                  |" +
                "\n|                                 || :|                                  |" +
                "\n|                                 :| |'         K A M I L                |" +
                "\n|                                 || :|            L U K A S Z           |" +
                "\n|                                 '| ||                M I C H A L       |" +
                "\n|                                 |: ':                   S Z Y M O N    |" +
                "\n|                                 || :|                                  |" +
                "\n|                           __..--:| |'--..__                            |" +
                "\n|                     _...-'  _.' |' :| '.__ '-..._                      |" +
                "\n|                   / -  ..---    '''''   ---...  _ \\                    |" +
                "\n|                   \\  _____  ..--   --..     ____  /                    |" +
                "\n|                    '-----....._________.....-----'                     |" +
                "\n|     Art by lgbeard                                                     |" +
                "\n|                                                                        |" +
                "\n ------------------------------------------------------------------------");
        STDOUT.info("\n");
    }

    private static void clearScreen() {
        STDOUT.info("\033[H\033[2J");
    }
}