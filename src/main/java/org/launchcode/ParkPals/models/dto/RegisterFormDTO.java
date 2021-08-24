package org.launchcode.ParkPals.models.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterFormDTO extends LoginFormDTO {

    @NotNull
    @Size(min=1, max=30, message = "First name is required.")
    private String firstName;

    @NotNull
    @Size(min=1, max=30, message = "Last name is required.")
    private String lastName;

    @NotNull
    @NumberFormat
    @Size(min=18, message = "Must be at least 18 to register.")
    private int age;

    @NotNull
    @Size(min=5, message = "Please enter local 5-digit zip code")
    private int zipCode;

    private String verifyPassword;

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

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

}
