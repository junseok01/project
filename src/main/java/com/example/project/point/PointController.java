package com.example.project.point;

import com.example.project.login.UserDTO;
import com.example.project.login.UserEntity;
import com.example.project.login.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PointController {
    @Autowired
    private PointService pointService;
    @Autowired
    private UserService userService;

    @GetMapping("/payment/buyTicket")
    public String buyTicket(@RequestParam("price") String price,
                            @RequestParam("transaction") String transaction,
                            HttpSession session){
        //DB에 55,000원 형식으로 저장되어있기에 바꾸는작업
        String cleanprice = price.replaceAll("[,원]","");
        int gymPrice = Integer.parseInt(cleanprice);
        UserDTO userDTO = (UserDTO)session.getAttribute("member");



        System.out.println("가격 : " + gymPrice + "  이름 : " + transaction );
        return "/main";
    }
}
