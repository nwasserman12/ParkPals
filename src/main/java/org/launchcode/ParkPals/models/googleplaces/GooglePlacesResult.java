package org.launchcode.ParkPals.models.googleplaces;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GooglePlacesResult {

    @JsonProperty("results")
    List<GooglePlacesObject> results;

    @JsonProperty("next_page_token")
    String next_page_token;

    @JsonProperty("status")
    String status;

    public GooglePlacesResult() {}

    public String getNext_page_token() {
        return next_page_token;
    }

    public void setNext_page_token(String next_page_token) {
        this.next_page_token = next_page_token;
    }

    public List<GooglePlacesObject> getResults() {
        return results;
    }

    public void setResults(List<GooglePlacesObject> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
