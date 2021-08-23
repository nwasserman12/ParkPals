package org.launchcode.ParkPals.models;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class Dog extends AbstractEntity {

    @NotBlank(message = "What is your dogs name?")

    @Size(min = 2, max = 20)
    private String name;

    @NotBlank(message = "What type of breed is your dog?")
    @Size(min = 2, max = 30)
    private String breed;

    @NotBlank(message = "How old is your dog?")
    @Size(min = 0, max = 30)
    private int age;

    @NotBlank(message = "How active is your dog?")
    @Size(min = 2, max = 20)
    private String activity;

    private Temperament type;

    @ManyToMany(mappedBy = "dogs")
    private final List<User> users = new ArrayList<>();

    public Dog(String name, String breed, String activity, Temperament type) {
        this.name = name;
        this.breed = breed;
        this.activity = activity;
        this.type = type;
    }

    public Dog() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Temperament getType() {
        return type;
    }

    public void setType(Temperament type) {
        this.type = type;
    }

    public List<User> getUsers() {
        return users;
    }
}
