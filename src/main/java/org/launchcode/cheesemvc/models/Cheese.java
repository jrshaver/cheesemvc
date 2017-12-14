package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Cheese")
@Table(name = "cheese")
public class Cheese {

    @Id
    @GeneratedValue
    private int id;

    @Size(min=3, max=15, message = "Cheese's name must be between 3-15 characters long.")
    private String cheeseName;

    @Size(min=1, message = "Please enter a description for your cheese.")
    private String description;

    private String cheeseRating;

    @ManyToOne
    private Category category;

    @ManyToMany
    private Set<Menu> menus = new HashSet<>();

    public Cheese(String cheeseName, String description) {
        this.cheeseName = cheeseName;
        this.description = description;
    }

    public Cheese() {
    }

    public int getId() {
        return id;
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

    public String getCheeseRating() {
        return cheeseRating;
    }

    public void setCheeseRating(String cheeseRating) {
        this.cheeseRating = cheeseRating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

}
