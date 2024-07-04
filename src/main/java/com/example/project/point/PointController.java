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
                            @RequestParam("description") String description,
                            @RequestParam("type") String type,
                            HttpSession session){
        //DB에 55,000원 형식으로 저장되어있기에 바꾸는작업
        String cleanprice = price.replaceAll("[,원]","");
        int gymPrice = Integer.parseInt(cleanprice);
        UserDTO userDTO = (UserDTO)session.getAttribute("member");
        description=description+"-"+type;

        if(userDTO.getPoint()-gymPrice<0){
            //가지고있는 돈보다 구매금액이 크기때문에 결제안됨
            // 추가 결제페이지로 이동



        }else{
            //결제진행
            UserDTO user = pointService.buyTicket(gymPrice, description, userDTO);
            session.setAttribute("member",user);
        }

        return "redirect:/main";
    }

    @GetMapping("/payment/addPoint")
    public String addpoint(@RequestParam("addpoint") int addpoint,
                            HttpSession session){

        UserDTO userDTO = (UserDTO)session.getAttribute("member");
        UserDTO user = pointService.addPoint(addpoint,userDTO);
        session.setAttribute("member",user);

        return "redirect:/main";
    }
}
