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
    public String displayCreateEventForm(@PathVariable String placeId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute(new Event());
        model.addAttribute("types", DogTemperament.values());
        model.addAttribute("activityLevels", DogActivity.values());
        model.addAttribute("user", user);
        model.addAttribute("park", parkRepository.findByPlaceId(placeId));
        return "event/create-event";
    }

    @PostMapping("{id}/create-event/{placeId}/details")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, @PathVariable String placeId, @ModelAttribute Park park,
                                         Errors errors, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        if(errors.hasErrors()) {
            model.addAttribute("types", DogTemperament.values());
            model.addAttribute("activityLevels", DogActivity.values());
            model.addAttribute("user", user);
            model.addAttribute("park", park);
            return "redirect:/user/create-event";
        }

        user.addEvents(newEvent);
        park.addEvents(newEvent);
        newEvent.addUserAttendees(user);
        newEvent.setPark(park);
        newEvent.setCreator(user);
        eventRepository.save(newEvent);
        return "redirect:/home";
    }
}
