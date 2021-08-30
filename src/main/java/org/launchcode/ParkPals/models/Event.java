package org.launchcode.ParkPals.models;

import java.util.Date;
import java.util.List;

public class Event extends AbstractEntity {

    private String name;

    private User creator;

    private Park park;

    private List<User> attendees;

    private Date date;

    private DogActivity desiredActivity;

    private DogTemperament desiredTemperament;
}
