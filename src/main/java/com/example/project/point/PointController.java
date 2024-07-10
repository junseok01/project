package com.example.project.point;

import com.example.project.login.UserDTO;
import com.example.project.login.UserEntity;
import com.example.project.login.UserService;
import jakarta.servlet.http.HttpSession;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.HashMap;

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
        String des=description;
        String cleanprice = price.replaceAll("[,원]","");
        int gymPrice = Integer.parseInt(cleanprice);

        UserDTO userDTO = (UserDTO)session.getAttribute("member");
        description=description+"-"+type;
        System.out.println(des+"$4444444444444444444444444444444444444444");
        if(userDTO.getPoint()-gymPrice<0){
            //가지고있는 돈보다 구매금액이 크기때문에 결제안됨
            // 추가 결제페이지로 이동
            //7월6일 자바스크립트로 금액부족시 결재진행 안되게 막아놈
        }else{
            //결제진행
            UserDTO user = pointService.buyTicket(gymPrice, description, userDTO);
            session.setAttribute("member",user);
            //문자보낼때마다 돈들어서 막아놈
            //테스트하고싶으면 주석지우고 실행
            //sendSms(user,des,type);
        }
        return "redirect:/main";
    }


    public void addpoint(int addpoint,
                            HttpSession session){

        UserDTO userDTO = (UserDTO)session.getAttribute("member");
        UserDTO user = pointService.addPoint(addpoint,userDTO);
        session.setAttribute("member",user);

    }

    @GetMapping("/payment/rechargePoint")
    public String rechargePoint(){
        return "/mypage/rechargePoint";
    }

    public void sendSms(UserDTO user,String description,String type) {
        LocalDate today = LocalDate.now();
        String api_key = "NCSPDPGSX6ABHYGX";
        String api_secret = "RVUUTLKZSOSJ9D7FLZW9BR2OIOZZIMOJ";
        Message coolsms = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();

        String userName=user.getName();
        String phoneNumber = user.getCellphoneNo().replace("-","");

        String text = userName + "님\n"
                + description + " 체육관 - " + type + "\n"
                + "구매 완료되었습니다.\n "
                + today.toString();
        System.out.println(userName);
        System.out.println(phoneNumber);
        System.out.println(text);

        params.put("to", "01062813528");
        params.put("from", phoneNumber);
        params.put("type", "SMS");
        params.put("text", text);
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }
}
