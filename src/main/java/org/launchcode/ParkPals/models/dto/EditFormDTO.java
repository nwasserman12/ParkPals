package org.launchcode.ParkPals.models.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EditFormDTO {
    @NotNull
    @Size(min=1, max=30, message = "First name is required.")
    private String firstName;

    @NotNull
    @Size(min=1, max=30, message = "Last name is required.")
    private String lastName;

    @NotNull
    @Min(value=18, message="Must be at least 18 to register.")
    private int age;

    @NotNull
    @Min(value=10000, message="6-digit zip code required.")
    private int zipCode;

    @Size(max=500, message = "Must not exceed 500 characters.")
    private String bio;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

}
