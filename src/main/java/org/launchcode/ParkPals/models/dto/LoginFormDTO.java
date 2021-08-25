package org.launchcode.ParkPals.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginFormDTO {

    @NotNull
    @NotBlank(message = "Invalid Username!")
    @Size(min = 3, max = 20, message = "Must be between 3 and 20 characters.")
    private String username;

    @NotNull
    @NotBlank(message = "Invalid Password!")
    @Size(min = 5, max = 30, message = "Must be between 5 and 30 characters.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
