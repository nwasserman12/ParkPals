package org.launchcode.ParkPals.models;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Dog extends AbstractEntity {

    @NotBlank(message = "What is your dogs name?")
    @Size(min = 2, max = 20)
    private String dogName;

    @NotBlank(message = "What type of breed is your dog?")
    @Size(min = 2, max = 30)
    private String dogBreed;

    @NotBlank(message = "How old is your dog?")
    @Size(min = 0, max = 30)
    private int dogAge;

    @NotBlank(message = "What type of temperament does your dog have?")
    @Size(min = 2, max = 20)
    private String dogTemperament;

    @NotBlank(message = "How active is your dog?")
    @Size(min = 2, max = 20)
    private String dogActivity;

    public Dog(String dogName, String dogBreed, int dogAge, String dogTemperament, String dogActivity) {
        super();
        this.dogName = dogName;
        this.dogBreed = dogBreed;
        this.dogAge = dogAge;
        this.dogTemperament = dogTemperament;
        this.dogActivity = dogActivity;
    }

    public Dog() {};

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public int getDogAge() {
        return dogAge;
    }

    public void setDogAge(int dogAge) {
        this.dogAge = dogAge;
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
