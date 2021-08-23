package org.launchcode.ParkPals.models.dto;

import org.launchcode.ParkPals.models.Dog;
import org.launchcode.ParkPals.models.User;

import javax.validation.constraints.NotNull;

public class UserDogDTO {

    @NotNull
    private User user;

    @NotNull
    private Dog dog;

    public UserDogDTO() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}
