package org.launchcode.ParkPals.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginFormDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 25, message = "Invalid Username. Must be between 3 and 25 characters.")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 25, message = "Invalid Password. Must be between 6 and 25 characters.")
    private String password;

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}

