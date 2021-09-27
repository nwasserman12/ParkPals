package org.launchcode.ParkPals.models.dto;

import org.apache.tomcat.jni.Local;
import org.launchcode.ParkPals.models.DogActivity;
import org.launchcode.ParkPals.models.DogTemperament;
import org.launchcode.ParkPals.models.Park;
import org.launchcode.ParkPals.models.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class EditEventFormDTO {

    @NotBlank(message = "Your event needs a name!")
    private String title;

    @NotNull(message= "What is today's date")
    private String date;

    @NotNull
    private DogActivity desiredActivity;

    @NotNull
    private DogTemperament desiredTemperament;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DogActivity getDesiredActivity() {
        return desiredActivity;
    }

    public void setDesiredActivity(DogActivity desiredActivity) {
        this.desiredActivity = desiredActivity;
    }

    public DogTemperament getDesiredTemperament() {
        return desiredTemperament;
    }

    public void setDesiredTemperament(DogTemperament desiredTemperament) {
        this.desiredTemperament = desiredTemperament;
    }

}
