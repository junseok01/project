package com.example.project.tube;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class tubeController {
    @GetMapping("/tube")
    public String tubepage(){
        return "tube/tubepage2";
    }
}
