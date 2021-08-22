package org.launchcode.ParkPals.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

public class Park {
    private int id;
    private static int nextId = 1;

    @NotBlank(message = "What is the name of the park?")
    @Size(min = 2, max = 50)
    private String parkName;

    @NotBlank(message = "Where is the park located?")
    @Size(min = 2, max = 50)
    private String parkLocation;

    @NotBlank(message = "Give a short description of the park.")
    @Size(min = 2, max = 500)
    private String parkDescription;

    public Park(String parkName, String parkLocation, String parkDescription) {
        this.parkName = parkName;
        this.parkLocation = parkLocation;
        this.parkDescription = parkDescription;
        this.id = nextId;
        nextId++;
    }

    public int getId() {
        return id;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getParkLocation() {
        return parkLocation;
    }

    public void setParkLocation(String parkLocation) {
        this.parkLocation = parkLocation;
    }

    public String getParkDescription() {
        return parkDescription;
    }

    public void setParkDescription(String parkDescription) {
        this.parkDescription = parkDescription;
    }
}
