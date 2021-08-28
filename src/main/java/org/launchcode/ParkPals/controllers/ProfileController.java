package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.DogRepository;
import org.launchcode.ParkPals.data.UserRepository;
import org.launchcode.ParkPals.models.Dog;
import org.launchcode.ParkPals.models.DogActivity;
import org.launchcode.ParkPals.models.DogTemperament;
import org.launchcode.ParkPals.models.User;
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
import java.util.Optional;

@Controller
@RequestMapping("user")
public class ProfileController {

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationController authenticationController;


    @GetMapping("{userId}")
    public String viewProfileById(@PathVariable Integer userId, Model model) {
        Optional optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = (User) optUser.get();
            model.addAttribute("user", user);
            return "user/profile";
        } else {
            return "redirect:../";
        }
    }



    @GetMapping("{userId}/add-dog")
    public String displayAddDogForm(@PathVariable Integer userId, Model model) {
        Optional<User> result = userRepository.findById(userId);
        User user = result.get();
        model.addAttribute(new Dog());
        model.addAttribute("types", DogTemperament.values());
        model.addAttribute("activityLevels", DogActivity.values());
        model.addAttribute("user", user);
        UserDogDTO userDog = new UserDogDTO();
        userDog.setUser(user);
        model.addAttribute("userDog", userDog);
        return "user/add-dog";
    }

    @PostMapping("{userId}/add-dog")
    public String processCreateDogForm(@PathVariable Integer userId, @ModelAttribute @Valid Dog newDog, UserDogDTO userDog,
                                       Errors errors, Model model) {
        Optional<User> result = userRepository.findById(userId);
        User user = result.get();
        if(errors.hasErrors()) {
            model.addAttribute("types", DogTemperament.values());
            model.addAttribute("activityLevels", DogActivity.values());
            return "redirect:/user/" + user.getId();
        }
        user.addDog(newDog);
        dogRepository.save(newDog);
        userRepository.save(user);
        model.addAttribute("user", user);
        return "user/profile";
    }

    @GetMapping("{userId}/edit")
    public String displayEditForm(Model model){
        model.addAttribute(new EditFormDTO());
        model.addAttribute("title", "Edit Profile");
        return "user/edit";
    }

    //TODO: Post mapping
    @PostMapping("{userId}/edit")
    public String processEditForm(@PathVariable Integer userId, @ModelAttribute @Valid EditFormDTO editFormDTO, Errors errors, HttpServletRequest request,
                                  Model model){
        Optional<User> result = userRepository.findById(userId);
        User user = result.get();
        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Profile");
            return "user/edit";
        }
        userRepository.save(user);
        model.addAttribute("user", user);
        return "user/profile";
    }


}
