package com.example.project.kakaoLogin;

import com.example.project.login.LoginService;
import com.example.project.login.UserDTO;
import com.example.project.login.UserEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Controller
public class kakaoController {
    private static final String CLIENT_ID = "9f03eb571cb673458cd44ea488fe57ad";
    private static final String REDIRECT_URI = "http://127.0.0.1:8088/kakaoLogin";
    private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";

    private LoginService loginService;

    public kakaoController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/kakaoLogin")
    public String kakaoLogin(@RequestParam("code") String code, HttpSession session) {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("카카오 로그인 들어옴" + code);
        KakaoAPI kakaoApi = new KakaoAPI();
        //토큰받기
        String accessToken = kakaoApi.getAccessToken(code);
        System.out.println(accessToken);

        // 2번 인증코드로 토큰 전달
        HashMap<String, Object> userInfo = kakaoApi.getUserInfo(accessToken);

        System.out.println("login info : " + userInfo.toString());


        String kakaoEmail = (String) userInfo.get("email");
        if (kakaoEmail != null && kakaoEmail.contains("@")) {
            String[] parts = kakaoEmail.split("@");
            kakaoEmail = parts[0] + "@kakao,com";
        }
        String kakaoName = (String) userInfo.get("nickname");
        //회원가입작업 시작
        if (loginService.search(kakaoEmail) == null) {
            UserEntity user = new UserEntity(kakaoEmail, kakaoName, "kakaoguest", "1", 2);
            loginService.register(user);
            UserDTO member = loginService.search(kakaoEmail);
            session.setAttribute("member", member);
            return "redirect:main";
        } else {
            UserDTO member = loginService.search(kakaoEmail);
            session.setAttribute("member", member);
            return "redirect:main";
        }
    }
}
