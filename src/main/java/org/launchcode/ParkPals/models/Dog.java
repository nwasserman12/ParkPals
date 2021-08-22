package org.launchcode.ParkPals.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Dog {
    @NotBlank(message = "Need to know your dog's name.")
    @Size(min = 2, max = 20)
    private String dogName;

    @NotBlank(message = "What type of breed is your dog?")
    @Size(min = 2, max = 30)
    private final String dogBreed;

    @NotBlank(message = "What type of temperament does your dog have?")
    @Size(min = 2, max = 20)
    private String dogTemperament;

    @NotBlank(message = "How active is your dog?")
    @Size(min = 2, max = 20)
    private String dogActivity;

    public Dog(String dogName, String dogBreed, String dogTemperament, String dogActivity) {
        this.dogName = dogName;
        this.dogBreed = dogBreed;
        this.dogTemperament = dogTemperament;
        this.dogActivity = dogActivity;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public String getDogTemperament() {
        return dogTemperament;
    }

    public void setDogTemperament(String dogTemperament) {
        this.dogTemperament = dogTemperament;
    }

    public String getDogActivity() {
        return dogActivity;
    }

    public void setDogActivity(String dogActivity) {
        this.dogActivity = dogActivity;
    }
}
