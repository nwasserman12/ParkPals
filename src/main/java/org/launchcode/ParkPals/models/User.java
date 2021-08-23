package org.launchcode.ParkPals.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 2, message = "Name is required")
    private String username;

    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @ManyToMany
    private final List<Dog> dogs = new ArrayList<>();

    @ManyToMany
    private final List<Park> parks = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public User() {}

    public String getUsername() {
        return username;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public List<Park> getParks() {
        return parks;
    }

    public void addDog(Dog dog) {
        this.dogs.add(dog);
    }

    public void addPark(Park park) {
        this.parks.add(park);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}