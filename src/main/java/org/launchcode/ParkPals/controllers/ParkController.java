package org.launchcode.ParkPals.controllers;


import org.launchcode.ParkPals.models.User;
import org.launchcode.ParkPals.models.googleplaces.GooglePlacesObject;
import org.launchcode.ParkPals.models.googleplaces.GooglePlacesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class ParkController {

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private GooglePlacesController googlePlacesController;

    @GetMapping("/parks")
    public String displayParkSearch(Model model) {

        return "park/park-selection";
    }

    @PostMapping("/parks")
    public String processParkSearch(@RequestParam String searchTerm, Model model, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute("user", user);
        GooglePlacesResult results = googlePlacesController.getParks(searchTerm);
        model.addAttribute("results", results.getResults());
        return "park/park-selection";
    }



}