package org.launchcode.ParkPals.controllers;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@RestController
public class GooglePlacesController {

    @RequestMapping(path = "/parks", method = RequestMethod.GET )
    public String getParks(@RequestParam String zipCode) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/place/textsearch/json?query=dog%20parks%20in%20" + zipCode +"&type=park&key=AIzaSyAIE0OD9TM_B0KosNrEsnwiVQS0h_Tr2dM")
                .get()
                .build();
        ResponseBody responseBody = client.newCall(request).execute().body();
        return responseBody.string();
    }
}