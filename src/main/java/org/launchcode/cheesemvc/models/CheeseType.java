package org.launchcode.cheesemvc.models;

public enum CheeseType {

    HARD ("Hard"),
    SOFT ("Soft"),
    FAKE ("Fake");

    public final String name;

    CheeseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
