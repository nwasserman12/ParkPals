package org.launchcode.ParkPals.controllers;

import org.launchcode.ParkPals.data.ParkRepository;
import org.launchcode.ParkPals.data.ReviewRepository;
import org.launchcode.ParkPals.models.Review;
import org.launchcode.ParkPals.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private ParkRepository parkRepository;

    @GetMapping("{userId}/review")
    public String ListOfReviews(Model model){
        model.addAttribute("review", reviewRepository.findAll());
        return "review/review";
    }

    @GetMapping("{userId}/create-review")
    public String displayCreateReviewForm(@PathVariable Integer id, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);
        model.addAttribute(new Review());
        model.addAttribute("user", user);
        model.addAttribute("park", parkRepository.findById(id));
        return "review/create-review";
    }



}
