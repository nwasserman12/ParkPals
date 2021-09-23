package org.launchcode.ParkPals.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dog extends AbstractEntity {

    @NotBlank(message = "What is your dogs name?")
    @Size(max = 20, message = "Max of 20 characters.")
    private String name;

    @NotBlank(message = "What type of breed is your dog?")
    private String breed;

    @NotNull(message = "How old is your dog?")
    private int age;

    @NotNull
    private DogActivity activity;

    @NotNull
    private DogTemperament type;

    @ManyToMany(mappedBy = "dogs")
    private final List<User> users = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private final List<Event> events = new ArrayList<>();

    public Dog(String name, String breed, int age, DogActivity activity, DogTemperament type) {
        this.name = name;
        this.breed = breed;
        this.age = age;
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

    public DogActivity getActivity() {
        return activity;
    }
  
    public void setActivity(DogActivity activity) {
        this.activity = activity;
    }

    public DogTemperament getType() {
        return type;
    }

    public void setType(DogTemperament type) {
        this.type = type;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {this.users.remove(user);}

    public List<User> getUsers() {

        return users;
    }

}
