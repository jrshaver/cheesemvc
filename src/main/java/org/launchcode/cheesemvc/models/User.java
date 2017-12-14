package org.launchcode.cheesemvc.models;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {

    @Size(min = 5, max = 15)
    @NotNull
    @Pattern(regexp = "^[A-Za-z]*$", message = "Username must only contain alphabetic characters.")
    private String username;

    @Email
    private String email;

    @Size(min = 6, message = "Password must be at least 6 characters long.")
    @NotNull
    private String password;

    @NotNull(message = "Passwords do not match.")
    private String verifyPassword;

    private int userId;

    private static int nextId = 1;

    public User() {
        userId = nextId;
        nextId++;
    }

    private void checkPassword() {

        if (password!=null && verifyPassword!=null && !(password.equals(verifyPassword))) {
            verifyPassword=null;
        }
    }

    private Date dateJoined;

    public String getDateJoined() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(dateJoined);
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        checkPassword();
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
        checkPassword();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
