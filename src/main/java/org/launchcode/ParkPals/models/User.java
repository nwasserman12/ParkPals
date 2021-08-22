package org.launchcode.ParkPals.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Entity
public class User extends AbstractEntity {

    @NotNull
    @Size(min=1, max=30, message = "First name is required.")
    private String firstName;

    @NotNull
    @Size(min=1, max=30, message = "Last name is required.")
    private String lastName;

    @NotNull
    @Size(min=18, message = "Must be at least 18 to register.")
    private int age;

    @NotNull
    @Size(min=5, message = "Please enter local 5-digit zip code")
    private int zipCode;

    private String bio;

    @NotNull
    @Size(min = 2, message = "Username is required.")
    private String username;

    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public User() {
    }

    public User(String username, String password) {
        super();
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}