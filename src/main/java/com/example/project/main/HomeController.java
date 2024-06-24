package com.example.project.main;

import com.example.project.login.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/main")
    public String home() {
        return "main"; // src/main/resources/templates/index.html
    }
}