package com.example.project.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/main")
    public String home() {
        return "main"; // src/main/resources/templates/index.html
    }
   @GetMapping("/test")
    public String test(){return "test";}
}
