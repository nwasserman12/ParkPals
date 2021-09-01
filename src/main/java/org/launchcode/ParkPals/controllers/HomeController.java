package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.EventRepository;
import org.launchcode.ParkPals.models.DogActivity;
import org.launchcode.ParkPals.models.DogTemperament;
import org.launchcode.ParkPals.models.Event;
import org.launchcode.ParkPals.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("user")
public class HomeController {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    EventRepository eventRepository;

    @RequestMapping("home")
    public String home(Model model) {
        model.addAttribute("event", eventRepository.findAll());
        return "home";
    }

}
