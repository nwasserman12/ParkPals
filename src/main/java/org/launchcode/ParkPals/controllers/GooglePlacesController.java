package org.launchcode.ParkPals.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.launchcode.ParkPals.data.ParkRepository;
import org.launchcode.ParkPals.models.Park;
import org.launchcode.ParkPals.models.User;
import org.launchcode.ParkPals.models.googleplaces.GooglePlacesObject;
import org.launchcode.ParkPals.models.googleplaces.GooglePlacesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@RestController
public class GooglePlacesController {

    @Autowired
    ParkRepository parkRepository;

    @RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
    public GooglePlacesResult getParks(@RequestParam String zipCode) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/place/textsearch/json?query=dog%20parks%20in%20" + zipCode +"&type=park&key=AIzaSyCIYdc3xjHEarOMM8S_O_AqZF0fkYqz4n0")
                .get()
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        ObjectMapper objectMapper = new ObjectMapper();
        GooglePlacesResult result = objectMapper.readValue(responseBody.string(), GooglePlacesResult.class);
        List<GooglePlacesObject> objects = result.getResults();

        for(GooglePlacesObject park : objects) {
            Park optPark = parkRepository.findByPlaceId(park.getPlaceId());
            if(optPark == null) {
                Park newPark = new Park(park.getBusinessStatus(), park.getPlaceId(), park.getName(), park.getFormattedAddress(), park.getRating(), park.getUserRatingsTotal());
                parkRepository.save(newPark);
            }

        }
        return result;
    }
}