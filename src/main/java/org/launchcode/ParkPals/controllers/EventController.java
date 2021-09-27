package org.launchcode.ParkPals.controllers;
 

import org.launchcode.ParkPals.data.DogRepository;
import org.launchcode.ParkPals.data.EventRepository;
import org.launchcode.ParkPals.data.ParkRepository;
import org.launchcode.ParkPals.models.*;
import org.launchcode.ParkPals.models.dto.EditDogFormDTO;
import org.launchcode.ParkPals.models.dto.EditFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    ParkRepository parkRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    DogRepository dogRepository;

    @GetMapping("create-event/{placeId}/details")
    public String displayCreateEventForm(@PathVariable String placeId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        Park park = parkRepository.findByPlaceId(placeId);
        List<Dog> attendingDogs = new ArrayList<>();
        model.addAttribute(new Event());
        model.addAttribute(placeId);
        model.addAttribute("types", DogTemperament.values());
        model.addAttribute("activityLevels", DogActivity.values());
        model.addAttribute("user", user);
        model.addAttribute("park", park);
        model.addAttribute("attendingDogs", attendingDogs);
        return "event/create-event";
    }

    @PostMapping("create-event/{placeId}/details")
    public String processCreateEventForm(@ModelAttribute @Valid Event event, @PathVariable String placeId, @RequestParam(required = false) int[] dogAttendees,
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

        if (dogAttendees != null) {
            for (int id : dogAttendees) {
                Optional<Dog> optionalDog = dogRepository.findById(id);
                Dog dog = optionalDog.get();
                dog.addEvents(event);
                event.addDogAttendee(dog);
            }
        }

        user.addEvents(event);
        park.addEvents(event);
        event.setPark(park);
        event.setCreator(user);

        eventRepository.save(event);
        return "redirect:/home";
    }

    @GetMapping("/event/{eventId}")
    public String viewEventProfileById(@PathVariable Integer eventId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        Optional optEvent = eventRepository.findById(eventId);
        if (optEvent.isPresent()) {
            Event event = (Event) optEvent.get();
            User creator = event.getCreator();
            model.addAttribute("event", event);
            model.addAttribute("creator", creator);
            model.addAttribute("user", user);

            return "event/event-profile";
        } else {
            return "redirect:../";
        }

    }

    @GetMapping("/event/{eventId}/join")
    public String displayJoinEvent(@PathVariable Integer eventId, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User userInSession = authenticationController.getUserFromSession(session);
        Optional<Event> optEvent = eventRepository.findById(eventId);
        if (optEvent.isPresent()) {
            Event event = (Event) optEvent.get();
            User creator = event.getCreator();
            model.addAttribute("dogs", event.filterUserDogs(userInSession.getDogs()));
            model.addAttribute("event", event);
            model.addAttribute("user", userInSession);
            model.addAttribute("creator", creator);
            eventRepository.save(event);
            return "event/join-event";
        } else {
            return "redirect:../";
        }
    }

    @PostMapping("/event/{eventId}/join")
    public String processJoinEvent(@PathVariable Integer eventId, Model model, @RequestParam(required = false) int[] dogAttendees, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Optional<Event> optEvent = eventRepository.findById(eventId);
        Event event = (Event) optEvent.get();
        User creator = event.getCreator();
        User userInSession = authenticationController.getUserFromSession(session);
        model.addAttribute("event", event);
        model.addAttribute("user", userInSession);
        model.addAttribute("creator", creator);
        if (dogAttendees != null) {
            for (int id : dogAttendees) {
                Optional<Dog> optionalDog = dogRepository.findById(id);
                Dog dog = optionalDog.get();
                if(!event.getDogAttendees().contains(dog)) {
                    userInSession.addEvents(event);
                    dog.addEvents(event);
                    event.addDogAttendee(dog);
                }
            }
        }
        eventRepository.save(event);
        return "event/event-profile";
    }

    @GetMapping("/event/{eventId}/leave")
    public String leaveEvent(@PathVariable Integer eventId, Model model, HttpServletRequest request) {
        Optional<Event> optEvent = eventRepository.findById(eventId);
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        List<Dog> userDogsInEvent = new ArrayList<>();
        if (optEvent.isPresent()) {
            Event event = (Event) optEvent.get();
            User creator = event.getCreator();
            for(Dog dog : event.getDogAttendees()) {
                if(user.getDogs().contains(dog)) {
                    userDogsInEvent.add(dog);
                    dog.removeEvents(event);
                }
            }
            event.removeUserAttendees(authenticationController.getUserFromSession(session));
            event.removeAllDogAttendees(userDogsInEvent);
            user.deleteEvents(event);
            model.addAttribute("event", event);
            model.addAttribute("user", user);
            model.addAttribute("creator", creator);
            eventRepository.save(event);
            return "event/event-profile";
        } else {
            return "redirect:../";
        }
    }

}
