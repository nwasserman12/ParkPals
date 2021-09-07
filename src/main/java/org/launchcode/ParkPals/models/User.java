package org.launchcode.ParkPals.models;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @NumberFormat
    private int age;

    @NotNull
    private int zipCode;

    private String bio;
  
    @ManyToMany
    private final List<Dog> dogs = new ArrayList<>();

    @OneToMany
    private final List<Event> events = new ArrayList<>();

    @NotNull
    @Size(min = 2, message = "Username is required.")
    private String username;

    @NotNull
    private String pwHash;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    
    public User(String username, String password, String firstName, String lastName, int age, int zipCode) {
        super();
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.zipCode = zipCode;
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

    public void addDog(Dog dog) {
        this.dogs.add(dog);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvents(Event event) {
        this.events.add(event);
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }
}