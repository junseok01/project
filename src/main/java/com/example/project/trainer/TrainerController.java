package com.example.project.trainer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrainerController {
    @GetMapping("/trainer")
    public String trainerpage(){
        return "trainer/trainerhome";
    }
    @GetMapping("/trainerread")
    public String gymread(){
        return "trainer/trainer_read";
    }

}
