package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.UserRepository;
import org.launchcode.ParkPals.models.User;
import org.launchcode.ParkPals.models.dto.LoginFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null){
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()){
            return null;
        }

        return user.get();
    }

    private static void setUserSessionKey(HttpSession session, User user){
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model){
        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "Log in");
        return "login";
    }
}
