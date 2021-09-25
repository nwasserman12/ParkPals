package org.launchcode.ParkPals.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

public class ParkMapController {
    @GetMapping("/parkmap")
    public String parkmap() {
        return "/parkmap";
    }
}
