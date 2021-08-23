package org.launchcode.ParkPals.models;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    @Size(min=1, max=30, message = "First name is required.")
    private String firstName;

    @NotNull
    @Size(min=1, max=30, message = "Last name is required.")
    private String lastName;

    @NotNull
    @NumberFormat
    @Size(min=18, message = "Must be at least 18 to register.")
    private int age;

    @NotNull
    @Size(min=5, message = "Please enter local 5-digit zip code")
    private int zipCode;

    private String bio;
  
    @ManyToMany
    private final List<Dog> dogs = new ArrayList<>();

    @ManyToMany
    private final List<Park> parks = new ArrayList<>();

    @NotNull
    @Size(min = 2, message = "Username is required.")
    private String username;

    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    
    public User(String username, String password, String firstName, String lastName, int age, int zipCode, String bio, List<Dog> dogs, List<Park> parks) {
        super();
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.zipCode = zipCode;
        this.bio = bio;
        this.dogs = dogs;
        this.parks = parks;
    }

    public User() {}
  
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

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