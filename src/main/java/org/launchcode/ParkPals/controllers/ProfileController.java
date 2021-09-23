package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.DogRepository;
import org.launchcode.ParkPals.data.EventRepository;
import org.launchcode.ParkPals.data.UserRepository;
import org.launchcode.ParkPals.models.*;
import org.launchcode.ParkPals.models.dto.EditDogFormDTO;
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
@RequestMapping("user")
public class ProfileController {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping("profile")
    public String userProfile(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute("user", user);
        return "user/profile";
    }


    @GetMapping("{userId}")
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



    @GetMapping("add-dog")
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

    @PostMapping("add-dog")
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


    @GetMapping("{userId}/dog/{dogId}")
    public String viewDogProfileById(@PathVariable Integer userId, @PathVariable Integer dogId, Model model) {
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            Optional optDog = dogRepository.findById(dogId);
            User user = (User) optUser.get();
            if (optDog.isPresent() && !user.getDogs().contains(optDog)) {
                Dog dog = (Dog) optDog.get();
                model.addAttribute("dog", dog);
                model.addAttribute("user", user);
                return "user/dog-profile";
            } else {
                return "redirect:../";
            }

        }

        return "redirect:../";
    }


    @GetMapping("{userId}/edit")
    public String displayEditForm(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute(new EditFormDTO());
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("{userId}/edit")
    public String processEditForm(@PathVariable Integer userId, @ModelAttribute @Valid EditFormDTO editFormDTO, @RequestParam(required = false) int[] eventIds, Errors errors, HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        if (errors.hasErrors()) {
            model.addAttribute("user", user);
            return "user/edit";
        }
        if (eventIds != null) {
            for (int id : eventIds) {
                Optional<Event> optionalEvent = eventRepository.findById(id);
                Event event = optionalEvent.get();
                user.deleteEvents(event);
                eventRepository.delete(event);
            }
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

    @GetMapping("{userId}/edit-dog/{dogId}")
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

    @PostMapping("{userId}/edit-dog/{dogId}")
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
            dogRepository.save(dog);
            userRepository.save(user);
        }

        return "redirect:/user/" + user.getId();
    }
}