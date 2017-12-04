package org.launchcode.cheesemvc.models;

import java.util.ArrayList;
import java.util.Date;

public class UserData {

    public static ArrayList<User> users = new ArrayList<>();

    public static void addUser(User newUser) {
        newUser.setDateJoined(new Date());
        users.add(newUser);
    }

    public static ArrayList<User> getAllUsers() {
        return users;
    }

    public static User getUserById(int id) {

        User aUser = null;

        for (User user: users) {
            if (user.getUserId() == id) {
                aUser = user;
            }
        }

        return aUser;
    }
}
