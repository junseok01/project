package com.example.project.request;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {
    @Autowired
    RequestService requestService;

    @PostMapping("/sendRequest")
    public String sendRequest(@RequestParam("request") String requestContent, HttpSession session) {
        String userId = (String) session.getAttribute("id");
        RequestDTO requestDTO = new RequestDTO(requestContent);
        requestService.saveRequest(userId, requestDTO);
        System.out.println("요청정보" + requestContent);
        return "redirect:/mypage";
    }

    @GetMapping("/viewRequests")
    public String viewRequests(Model model) {
        System.out.println("요청뷰" + requestService.getAllRequests());
        model.addAttribute("requests", requestService.getAllRequests());
        return "admin/requests";
    }

}
