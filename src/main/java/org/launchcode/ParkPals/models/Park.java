package org.launchcode.ParkPals.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Park extends AbstractEntity {

    @NotBlank(message = "What is the name of the park?")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "Where is the park located?")
    @Size(min = 2, max = 50)
    private String location;

    @NotBlank(message = "Give a short description of the park.")
    @Size(min = 2, max = 500)
    private String description;

    @ManyToMany(mappedBy = "parks")
    private final List<User> users = new ArrayList<>();

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

    public List<User> getUsers() {
        return users;
    }
}
