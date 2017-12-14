package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Menu")
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue
    private int id;

    @Size(min=3, max=15, message = "Cheese's name must be between 3-15 characters long.")
    private String name;

    @ManyToMany(mappedBy = "menus")
    private Set<Cheese> cheeses = new HashSet<>();

    public void addItem(Cheese item) {
        cheeses.add(item);
        item.getMenus().add(this);
    }

    public Menu(String name) {
        this.name = name;
    }

    public Menu() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Cheese> getCheeses() {
        return cheeses;
    }
}
