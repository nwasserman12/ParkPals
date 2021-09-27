package org.launchcode.ParkPals.models;

public enum DogTemperament {
    REACTIVE("Reactive", 1),
    SELECTIVE("Selective", 2),
    FRIENDLY("Friendly", 3),
    ALL("All", 0);

    private final String temperamentType;
    private final Integer temperamentValue;

    DogTemperament(String temperamentType, Integer temperamentValue) {
        this.temperamentType = temperamentType;
        this.temperamentValue = temperamentValue;
    }

    public String getTemperamentType() {
        return temperamentType;
    }

    public Integer getTemperamentValue() {
        return temperamentValue;
    }
}
