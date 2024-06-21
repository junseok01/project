package com.example.project.admin;

import com.example.project.login.UserDTO;
import com.example.project.login.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/memberList")
    public String memberList(HttpSession session){
        List<UserDTO> UserList = userService.findAll();
        session.setAttribute("memberList",UserList);
        return "/admin/memberList";
    }
    @GetMapping("/admin/deleteMember")
    public String deleteMember(@RequestParam("id") String id){
        userService.deleteMember(id);
        return "redirect:/admin/memberList";
    }
}
