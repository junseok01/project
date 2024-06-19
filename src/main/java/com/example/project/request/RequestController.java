package com.example.project.request;


import com.example.project.login.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
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
        UserDTO member = (UserDTO)session.getAttribute("member");
        String userId = (String)member.getLoginId();
        System.out.println(userId);
        RequestDTO requestDTO = new RequestDTO(requestContent);
        requestService.saveRequest(userId, requestDTO);
        System.out.println("요청정보" + requestContent);
        return "redirect:/mypage";
    }

    @GetMapping("/viewRequests")
    public String viewRequests(Model model) {
        System.out.println("요청뷰" + requestService.getAllRequests());
        model.addAttribute("requests", requestService.getAllRequests());
        System.out.println(requestService.getAllRequests());
        return "admin/requests";
    }

}
