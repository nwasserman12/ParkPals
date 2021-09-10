package org.launchcode.ParkPals.models.googleplaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GooglePlacesObject {

    @JsonProperty("place_id")
    String placeId;

    @JsonProperty("business_status")
    String businessStatus;

    @JsonProperty("formatted_address")
    String formattedAddress;

    @JsonProperty("icon")
    String iconURL;

    @JsonProperty("name")
    String name;

    @JsonProperty("rating")
    Integer rating;

    @JsonProperty("user_ratings_total")
    Integer userRatingsTotal;

    @JsonProperty("geometry")
    GooglePlacesGeometry geometry;

    public GooglePlacesObject() {}

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public GooglePlacesGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GooglePlacesGeometry geometry) {
        this.geometry = geometry;
    }
}
