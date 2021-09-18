package org.launchcode.ParkPals.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event extends AbstractEntity {

    @NotBlank(message = "Your event needs a name!")
    private String title;

    @ManyToOne
    private User creator;

    @ManyToOne(cascade = CascadeType.ALL)
    private Park park;

    @NotNull(message = "Please enter a date for your event!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    private DogActivity desiredActivity;

    @NotNull
    private DogTemperament desiredTemperament;

    @ElementCollection
    private List<User> userAttendees = new ArrayList<>();

    @ElementCollection
    private List<Dog> dogAttendees = new ArrayList<>();

    public Event(String title, Park park, Date date, DogActivity desiredActivity, DogTemperament desiredTemperament, List<Dog> dogAttendees) {
        this.title = title;
        this.park = park;
        this.date = date;
        this.desiredActivity = desiredActivity;
        this.desiredTemperament = desiredTemperament;
        this.dogAttendees = dogAttendees;
    }

    public Event() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Park getPark() {
        return park;
    }

    public void setPark(Park park) {
        this.park = park;
    }

    public String getParkName() {
        return park.getName();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DogActivity getDesiredActivity() {
        return desiredActivity;
    }

    public void setDesiredActivity(DogActivity desiredActivity) {
        this.desiredActivity = desiredActivity;
    }

    public DogTemperament getDesiredTemperament() {
        return desiredTemperament;
    }

    public void setDesiredTemperament(DogTemperament desiredTemperament) {
        this.desiredTemperament = desiredTemperament;
    }

    public List<User> getUserAttendees() {
        return userAttendees;
    }

    public void addUserAttendees(User user) {
        this.userAttendees.add(user);
    }

    public List<Dog> getDogAttendees() {
        return dogAttendees;
    }

}