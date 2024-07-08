package com.example.project.login;



import com.example.project.admin.RestController;
import com.example.project.admin.VisitorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import java.math.BigInteger;
import java.security.SecureRandom;
//https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=GHj3n2d3pwyhTqPAu8cq&client_secret=ef3yePxiz0&access_token=AAAAN8OtHAnXBpqhceBKpEE3GLPVjpuCGBTp9GgTwkWjx9ud9D3t8ZO4J9l5v1fDdmKIGxMcGkE4qITGCzSdIpIrbpo&service_provider=NAVER
@Controller
public class LoginController {
    private LoginService service;

    @Autowired
    private VisitorService visitorService;




    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }
    @GetMapping("/login")
    public String login(Model model,HttpSession session) {
        String stateToken = generateState();
        System.out.println(stateToken);
        session.setAttribute("state",stateToken);
        model.addAttribute("stateToken",stateToken);
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("id") String id, @RequestParam("pass") String pass, HttpSession session) {
        System.out.println(id);
        System.out.println(pass);
        UserDTO member = service.search(id);
        System.out.println("멤버의 값" + member);
        if(member == null){
            System.out.println("로그인실패 아이디 없음!!");
            return "redirect:/login";
        }
        if(member.getLoginId().equals(id) && member.getLoginPw().equals(pass)){
            System.out.println("로그인성공!!");
            session.setAttribute("member",member);
            visitorService.incrementVisitorCount();
            return "redirect:/main";
        }else{
            System.out.println("로그인실패 비밀번호 불일치!!");
            return "redirect:/login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("member");
        return "redirect:/main";
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "register/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("loginId") String loginId, @RequestParam("loginPw") String loginPw,
                           @RequestParam("loginPwConfirm") String loginPwConfirm, @RequestParam("name") String name
            , @RequestParam("nickname") String nickname,
                           @RequestParam("cellphoneNo") String cellphoneNo) {
        service.register(new UserEntity(loginId,loginPw,name,nickname,"1",cellphoneNo,0));
        System.out.println("회원가입 완료!!!");

        return "redirect:/main";
    }


    // CSRF 방지를 위한 상태 토큰 생성 메서드
    private String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    @GetMapping("/naver")
    public String loginTest() {

        return "/test/naverlogin";
    }

    @GetMapping("/naver_login")
    public String logintest2() {
        return "redirect:/main";
    }


}
