package org.launchcode.ParkPals.models.dto;

import org.launchcode.ParkPals.models.Park;
import org.launchcode.ParkPals.models.User;

import javax.validation.constraints.NotNull;

public class UserParkDTO {

    @NotNull
    private User user;

    @NotNull
    private Park park;

    public UserParkDTO() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }
}
