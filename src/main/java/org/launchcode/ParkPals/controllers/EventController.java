package org.launchcode.ParkPals.controllers;
 
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
public class EventController {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    EventRepository eventRepository;

    @GetMapping("{id}/create-event")
    public String displayCreateEventForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute(new Event());
        model.addAttribute("types", DogTemperament.values());
        model.addAttribute("activityLevels", DogActivity.values());
        model.addAttribute("user", user);
        return "event/create-event";
    }

    @PostMapping("{id}/create-event")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent,
                                         Errors errors, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        if(errors.hasErrors()) {
            model.addAttribute("types", DogTemperament.values());
            model.addAttribute("activityLevels", DogActivity.values());
            return "redirect:/user/create-event";
        }
        user.addEvents(newEvent);
        newEvent.addUserAttendees(user);
        eventRepository.save(newEvent);
        model.addAttribute("user", user);
        return "redirect:/home";
    }
}
