package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.DogRepository;
import org.launchcode.ParkPals.data.EventRepository;
import org.launchcode.ParkPals.data.UserRepository;
import org.launchcode.ParkPals.models.*;
import org.launchcode.ParkPals.models.dto.EditFormDTO;
import org.launchcode.ParkPals.models.dto.LoginFormDTO;
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
    public String processAddDogForm(@ModelAttribute @Valid Dog newDog, UserDogDTO userDog,
                                       Errors errors, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        if(errors.hasErrors()) {
            model.addAttribute("types", DogTemperament.values());
            model.addAttribute("activityLevels", DogActivity.values());
            return "redirect:/user/" + user.getId();
        }
        user.addDog(newDog);
        newDog.addUser(user);
        dogRepository.save(newDog);
        userRepository.save(user);
        model.addAttribute("user", user);
        return "redirect:/user/{userId}/edit(userId=${user.getId()})";
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

//    @GetMapping("{userId}/dog/{dogId}/edit-dog")
//    public String displayEditDogForm(@PathVariable Integer userId, @PathVariable Integer dogId, Model model) {
//        Optional optUser = userRepository.findById(userId);
//        if (optUser.isPresent()) {
//            Optional optDog = dogRepository.findById(dogId);
//            User user = (User) optUser.get();
//            if (optDog.isPresent() && !user.getDogs().contains(optDog)) {
//                Dog dog = (Dog) optDog.get();
//                UserDogDTO userDog = new UserDogDTO();
//                model.addAttribute("userDog", userDog);
//                model.addAttribute("types", DogTemperament.values());
//                model.addAttribute("activityLevels", DogActivity.values());
//            }
//        }
//        return "user/edit-dog";
//    }

    @GetMapping("{userId}/edit")
    public String displayEditForm(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute(new EditFormDTO());
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("{userId}/edit")
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

        return "redirect:/user/{userId}";
    }




  
}