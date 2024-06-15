package com.example.project.gym;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GymController {
    @GetMapping("/gym")
    public String gympage(){
        return "gym/gymhome";
    }
    @GetMapping("/gymread")
    public String gymread(){
        return "gym/gym_read";
    }
}
