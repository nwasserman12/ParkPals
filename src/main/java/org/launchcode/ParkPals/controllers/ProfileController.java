package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.DogRepository;
import org.launchcode.ParkPals.data.EventRepository;
import org.launchcode.ParkPals.data.ParkRepository;
import org.launchcode.ParkPals.data.UserRepository;
import org.launchcode.ParkPals.models.*;
import org.launchcode.ParkPals.models.dto.EditDogFormDTO;
import org.launchcode.ParkPals.models.dto.EditEventFormDTO;
import org.launchcode.ParkPals.models.dto.EditFormDTO;
import org.launchcode.ParkPals.models.dto.UserDogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping("user/profile")
    public String userProfile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute("user", user);
        return "user/profile";
    }


    @GetMapping("user/{userId}")
    public String viewProfileById(@PathVariable Integer userId, Model model, HttpServletRequest request) {
        Optional optUser = userRepository.findById(userId);
        HttpSession session = request.getSession();
        User loggedUser = authenticationController.getUserFromSession(session);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
            model.addAttribute("loggedUser", loggedUser);
            return "user/profile";
        } else {
            return "redirect:/";
        }
    }



    @GetMapping("user/add-dog")
    public String displayAddDogForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute(new Dog());
        model.addAttribute("types", DogTemperament.values());
        model.addAttribute("activityLevels", DogActivity.values());
        model.addAttribute("user", user);
        UserDogDTO userDog = new UserDogDTO();
        userDog.setUser(user);
        model.addAttribute("userDog", userDog);
        return "user/add-dog";
    }

    @PostMapping("user/add-dog")
    public String processAddDogForm(@ModelAttribute @Valid Dog newDog,
                                       Errors errors, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        if(errors.hasErrors()) {
            model.addAttribute("types", DogTemperament.values());
            model.addAttribute("activityLevels", DogActivity.values());
            return "user/add-dog";
        }
        user.addDog(newDog);
        newDog.addUser(user);
        dogRepository.save(newDog);
        userRepository.save(user);
        model.addAttribute("userId", user.getId());
        return "redirect:/user/{userId}/edit";
    }


    @GetMapping("dog/{dogId}")
    public String viewDogProfileById(@PathVariable Integer dogId, Model model) {
        Optional optDog = dogRepository.findById(dogId);
        if (optDog.isPresent()) {
            Dog dog = (Dog) optDog.get();
            User owner = dog.getUsers().get(0);
            model.addAttribute("dog", dog);
            model.addAttribute("dogId", dogId);
            model.addAttribute("user", owner);
            return "user/dog-profile";
        } else {
            return "redirect:../";
        }
    }


    @GetMapping("user/{userId}/edit")
    public String displayEditForm(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute(new EditFormDTO());
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("user/{userId}/edit")
    public String processEditForm(@PathVariable Integer userId, @ModelAttribute @Valid EditFormDTO editFormDTO, Errors errors, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            return "user/edit";
        }

        user.setFirstName(editFormDTO.getFirstName());
        user.setLastName(editFormDTO.getLastName());
        user.setAge(editFormDTO.getAge());
        user.setZipCode(editFormDTO.getZipCode());
        user.setBio(editFormDTO.getBio());
        userRepository.save(user);
        model.addAttribute("user", user);

        return "redirect:/user/" + user.getId();
    }

    @GetMapping("user/{userId}/edit-dog/{dogId}")
    public String displayEditDogForm(Model model, @PathVariable Integer dogId, HttpServletRequest request) {
        User user = authenticationController.getUserFromSession(request.getSession());
        Optional<Dog> optDog = dogRepository.findById(dogId);
        Dog dog = optDog.get();
        model.addAttribute("user", user);
        model.addAttribute("dog", dog);
        model.addAttribute("types", DogTemperament.values());
        model.addAttribute("activityLevels", DogActivity.values());
        model.addAttribute(new EditDogFormDTO());
        return "user/edit-dog";
    }

    @PostMapping("user/{userId}/edit-dog/{dogId}")
    public String processEditDogForm(@RequestParam(value = "ids" , required = false) int[] ids, @ModelAttribute @Valid EditDogFormDTO editDogFormDTO, @PathVariable Integer dogId, HttpServletRequest request) {
        User user = authenticationController.getUserFromSession(request.getSession());
        Optional<Dog> optDog = dogRepository.findById(dogId);
        Dog dog = optDog.get();

        dog.setName(editDogFormDTO.getName());
        dog.setAge(editDogFormDTO.getAge());
        dog.setBreed(editDogFormDTO.getBreed());
        dog.setActivity(editDogFormDTO.getActivity());
        dog.setType(editDogFormDTO.getType());
        dogRepository.save(dog);

        if(ids != null) {
            dog.removeUser(user);
            user.removeDog(dog);
            dogRepository.delete(dog);
        }

        return "redirect:/user/" + user.getId();
    }

    @GetMapping("user/{userId}/edit-event/{eventId}")
    public String displayEditEventForm(Model model, @PathVariable Integer eventId, HttpServletRequest request) {
        User user = authenticationController.getUserFromSession(request.getSession());
        Optional<Event> optEvent = eventRepository.findById(eventId);
        Event event = optEvent.get();
        model.addAttribute("user", user);
        model.addAttribute("event", event);
        model.addAttribute("types", DogTemperament.values());
        model.addAttribute("activityLevels", DogActivity.values());
        model.addAttribute(new EditEventFormDTO());
        return "event/edit";
    }

    @PostMapping("user/{userId}/edit-event/{eventId}")
    public String processEditEventForm(@RequestParam(value = "id" , required = false) int[] id, @RequestParam(value = "userIds" , required = false) int[] userIds, @RequestParam(value = "dogIds" , required = false) int[] dogIds, @ModelAttribute @Valid EditEventFormDTO editEventFormDTO, @PathVariable Integer eventId, HttpServletRequest request) {
        User user = authenticationController.getUserFromSession(request.getSession());
        Optional<Event> optEvent = eventRepository.findById(eventId);
        Event event = optEvent.get();

        event.setTitle(editEventFormDTO.getTitle());
        event.setDate(editEventFormDTO.getDate());
        event.setDesiredActivity(editEventFormDTO.getDesiredActivity());
        event.setDesiredTemperament(editEventFormDTO.getDesiredTemperament());
        eventRepository.save(event);

        List<Dog> allDogAttendees = event.getDogAttendees();
        List<User> allUserAttendees = event.getUserAttendees();

        if(id != null) {
            for(Dog dog : allDogAttendees) {
                dog.removeEvents(event);
            }
            event.getDogAttendees().removeAll(allDogAttendees);
            event.getUserAttendees().removeAll(allUserAttendees);
            user.deleteEvents(event);
            eventRepository.delete(event);
        }

        if(userIds != null) {
            for(Integer userId : userIds) {
                Optional<User> optUser = userRepository.findById(userId);
                User thisUser = optUser.get();
                thisUser.deleteEvents(event);
            }
        }

        if(dogIds != null) {
            for(Integer dogId : dogIds) {
                Optional<Dog> optDog = dogRepository.findById(dogId);
                Dog thisDog = optDog.get();
                thisDog.removeEvents(event);
            }
        }

        return "redirect:/user/" + user.getId();
    }



}