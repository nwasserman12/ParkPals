package org.launchcode.ParkPals.models;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Park extends AbstractEntity {

    private static final String URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=dog%20parks%20in%20{zipCode}&type=park";

    @NotBlank(message = "What is the name of the park?")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "Where is the park located?")
    @Size(min = 2, max = 50)
    private String location;

    @NotBlank(message = "Give a short description of the park.")
    @Size(min = 2, max = 500)
    private String description;

    @OneToMany
    private List<Event> events;

    public Park(String name, String location, String description) {
        super();
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public Park() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvents(Event event) {
        this.events.add(event);
    }


}
