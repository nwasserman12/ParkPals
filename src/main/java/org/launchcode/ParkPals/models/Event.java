package org.launchcode.ParkPals.models;

import org.apache.tomcat.jni.Local;
import org.launchcode.ParkPals.data.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Entity
public class Event extends AbstractEntity {

    @NotBlank(message = "Your event needs a name!")
    private String title;

    @ManyToOne
    private User creator;

    @ManyToOne
    private Park park;

    @NotNull
    private String date;

    @NotNull
    private DogActivity desiredActivity;

    @NotNull
    private DogTemperament desiredTemperament;

    @ManyToMany(mappedBy = "events")
    private List<User> userAttendees = new ArrayList<>();

    @ManyToMany(mappedBy = "events")
    private List<Dog> dogAttendees = new ArrayList<>();

    public Event(String title, Park park, String date, DogActivity desiredActivity, DogTemperament desiredTemperament) {
        this.title = title;
        this.park = park;
        this.date = date;
        this.desiredActivity = desiredActivity;
        this.desiredTemperament = desiredTemperament;
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
        Park park = this.park;
        String name = park.getName();
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public void removeUserAttendees(User user) {
        this.userAttendees.remove(user);
    }

    public List<Dog> getDogAttendees() {
        return dogAttendees;
    }

    public void addDogAttendee(Dog dog) {
        this.dogAttendees.add(dog);
    }

    public void removeDogAttendee(Dog dog) {
        this.dogAttendees.remove(dog);
    }

    public String dateToString() {
        int hour = Integer.parseInt(date.substring(11,13));
        int minute = Integer.parseInt(this.date.substring(14,16));
        if(hour > 12) {
            hour = hour - 12;
            return date = this.date.toString().substring(5, 10) + "-" + this.date.substring(0,4) + " at " + hour + ":" + minute + " PM";

        }
        return date = this.date.toString().substring(5, 10) + "-" + this.date.substring(0,4) + " at " + hour + ":" + minute + " AM";
    }
}
