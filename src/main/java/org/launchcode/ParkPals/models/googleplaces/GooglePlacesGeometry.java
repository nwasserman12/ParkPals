package org.launchcode.ParkPals.models.googleplaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GooglePlacesGeometry {

    @JsonProperty("location")
    GooglePlacesLocation googlePlacesLocation;

    public GooglePlacesGeometry() {}

    public GooglePlacesLocation getGooglePlacesLocation() {
        return googlePlacesLocation;
    }

    public void setGooglePlacesLocation(GooglePlacesLocation googlePlacesLocation) {
        this.googlePlacesLocation = googlePlacesLocation;
    }
}
