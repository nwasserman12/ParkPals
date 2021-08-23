package org.launchcode.ParkPals.models;

public enum Temperament {
    AGGRESSIVE("Aggressive"),
    NEUTRAL("Neutral"),
    PASSIVE("Passive");

    private final String temperamentType;

    Temperament(String temperamentType) {
        this.temperamentType = temperamentType;
    }

    public String getTemperamentType() {
        return temperamentType;
    }
}
