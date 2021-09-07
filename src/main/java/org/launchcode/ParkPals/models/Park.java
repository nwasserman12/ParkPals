package org.launchcode.ParkPals.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Park extends AbstractEntity {

    private String placeId;

    private String businessStatus;

    private String name;

    private String address;

    private Integer rating;

    private Integer userRatingsTotal;

    @OneToMany
    private List<Event> events;

    public Park(String businessStatus, String placeId, String name, String address, Integer rating, Integer userRatingsTotal) {
        super();
        this.businessStatus = businessStatus;
        this.placeId = placeId;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.userRatingsTotal = userRatingsTotal;
    }

    public Park() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String location) {
        this.address = location;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvents(Event event) {
        this.events.add(event);
    }

    public String getPlace_id() {
        return placeId;
    }

    public void setPlace_id(String placeId) {
        this.placeId = placeId;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getUserRatingsTotal() {
        return userRatingsTotal;
    }

    public void setUserRatingsTotal(Integer userRatingsTotal) {
        this.userRatingsTotal = userRatingsTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Park entity = (Park) o;
        return placeId == entity.placeId;
    }
}
