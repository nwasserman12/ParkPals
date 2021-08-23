package org.launchcode.ParkPals.models;

public enum DogTemperament {
    AGGRESSIVE("Aggressive"),
    NEUTRAL("Neutral"),
    PASSIVE("Passive");

    private final String temperamentType;

    DogTemperament(String temperamentType) {
        this.temperamentType = temperamentType;
    }

    public String getTemperamentType() {
        return temperamentType;
    }
}
