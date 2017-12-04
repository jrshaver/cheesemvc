package org.launchcode.cheesemvc.models;

import javax.validation.constraints.Size;

public class Cheese {

    @Size(min=3, max=15, message = "Cheese's name must be between 3-15 characters long.")
    String cheeseName;

    @Size(min=1, message = "Please enter a description for your cheese.")
    String description;

    CheeseType cheeseType;

    @Size(min=1, max=5)
    String cheeseRating;

    int cheeseId;

    static int nextId = 1;

    public Cheese() {
        cheeseId = nextId;
        nextId++;
    }

    public String getCheeseName() {
        return cheeseName;
    }

    public void setCheeseName(String aCheeseName) {
        cheeseName = aCheeseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String aDescription) {
        description = aDescription;
    }

    public CheeseType getCheeseType() {
        return cheeseType;
    }

    public void setCheeseType(CheeseType cheeseType) {
        this.cheeseType = cheeseType;
    }

    public int getCheeseId() {
        return cheeseId;
    }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public String getCheeseRating() {
        return cheeseRating;
    }

    public void setCheeseRating(String cheeseRating) {
        this.cheeseRating = cheeseRating;
    }
}
