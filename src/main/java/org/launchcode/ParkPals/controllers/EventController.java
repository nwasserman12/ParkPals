package org.launchcode.ParkPals.controllers;
 

import org.launchcode.ParkPals.data.EventRepository;
import org.launchcode.ParkPals.data.ParkRepository;
import org.launchcode.ParkPals.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class EventController {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    ParkRepository parkRepository;

    @Autowired
    EventRepository eventRepository;

    @GetMapping("{id}/create-event/{placeId}/details")
    public String displayCreateEventForm(@PathVariable String placeId, Model model, HttpServletRequest request, Event event) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute(new Event());
        model.addAttribute(placeId);
        model.addAttribute("types", DogTemperament.values());
        model.addAttribute("activityLevels", DogActivity.values());
        model.addAttribute("user", user);
        Park park = parkRepository.findByPlaceId(placeId);
        model.addAttribute("park", park);
        return "event/create-event";
    }

    @PostMapping("{id}/create-event/{placeId}/details")
    public String processCreateEventForm(@ModelAttribute @Valid Event event, @PathVariable String placeId, Dog[] dogsAttending,
                                         Errors errors, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        Park park = parkRepository.findByPlaceId(placeId);
        if(errors.hasErrors()) {
            model.addAttribute("types", DogTemperament.values());
            model.addAttribute("activityLevels", DogActivity.values());
            model.addAttribute("user", user);
            model.addAttribute("park", park);
            return "event/create-event";
        }


        user.addEvents(event);
        park.addEvents(event);
        event.addUserAttendees(user);
        event.setPark(park);
        event.setCreator(user);
        eventRepository.save(event);
        return "redirect:/user/{userId}/home(userId=${user.getId()})";
    }
}
