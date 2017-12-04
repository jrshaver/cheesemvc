package org.launchcode.cheesemvc.models;

import java.util.ArrayList;

public class CheeseData {

    public static ArrayList<Cheese> cheeses = new ArrayList<>();

    public static ArrayList<Cheese> getAll() {
        return cheeses;
    }

    public static void addCheese(Cheese newCheese) {
        cheeses.add(newCheese);
    }

    public static void removeCheese(String cheeseToRemove) {
        for (Cheese cheese : cheeses) {
            if (cheese.getCheeseName().equals(cheeseToRemove)) {
                cheeses.remove(cheese);
                break;
            }
        }
    }

    public static void editCheese(int cheeseId, Cheese editedCheese) {
        Cheese cheeseToEdit = getById(cheeseId);
        String newName = editedCheese.getCheeseName();
        cheeseToEdit.setCheeseName(newName);
        String newDescription = editedCheese.getDescription();
        cheeseToEdit.setDescription(newDescription);
        CheeseType newType = editedCheese.getCheeseType();
        cheeseToEdit.setCheeseType(newType);
        String newRating = editedCheese.getCheeseRating();
        cheeseToEdit.setCheeseRating(newRating);
    }

    public static Cheese getById(int id) {
        Cheese aCheese = null;

        for (Cheese cheese: cheeses) {
            if (cheese.getCheeseId() == id) {
                aCheese = cheese;
            }
        }

        return aCheese;
    }
}
