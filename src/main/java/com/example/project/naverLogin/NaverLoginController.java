package com.example.project.naverLogin;

import com.example.project.admin.VisitorService;
import com.example.project.login.LoginService;
import com.example.project.login.UserDTO;
import com.example.project.login.UserEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class NaverLoginController {
    private LoginService service;

    @Autowired
    VisitorService visitorService;

    @Autowired
    public NaverLoginController(LoginService service) {
        this.service = service;
    }

    @GetMapping("/naverLogin")
    public String naverLogin(@RequestParam("code") String code, @RequestParam("state") String stateToken, Model model,
                             HttpSession session) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();


        // 액세스 토큰 요청을 위한 헤더 및 파라미터 설정
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> accessTokenParams = new LinkedMultiValueMap<>();
        accessTokenParams.add("grant_type", "authorization_code");
        accessTokenParams.add("client_id", "GHj3n2d3pwyhTqPAu8cq");
        accessTokenParams.add("client_secret", "ef3yePxiz0");
        accessTokenParams.add("code", code); // 응답으로 받은 코드
        accessTokenParams.add("state", stateToken); // 응답으로 받은 상태

        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(accessTokenParams, headers);

        ResponseEntity<String> accessTokenResponse = restTemplate.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                accessTokenRequest,
                String.class
        );

        // 액세스 토큰 추출
        String responseBody = accessTokenResponse.getBody();
        String accessToken = extractAccessToken(responseBody); // JSON에서 액세스 토큰 추출하는 메서드


        // 사용자 프로필 정보를 요청
        String profileUrl = "https://openapi.naver.com/v1/nid/me";
        HttpHeaders profileHeaders = new HttpHeaders();
        profileHeaders.set("Authorization", "Bearer " + accessToken);
        profileHeaders.set("Content-Type", "application/json");

        HttpEntity<String> profileRequest = new HttpEntity<>(profileHeaders);
        ResponseEntity<String> profileResponse = restTemplate.exchange(
                profileUrl,
                HttpMethod.GET,
                profileRequest,
                String.class
        );

        //profileJSON -> 네이버간편로그인으로 로그인시 받아오는 JSON형태의 데이터값
        String profileJSON = profileResponse.getBody();
        try {
            JsonNode profileJsonNode = objectMapper.readTree(profileJSON);
            String userName = profileJsonNode.get("response").get("name").asText();
            String userEmail = profileJsonNode.get("response").get("email").asText();
            String userPhone = profileJsonNode.get("response").get("mobile").asText();

            //네이버 간편로그인시 DB에 이메일이 없다면 즉 처음 로그인시 회원가입진행
            if(service.search(userEmail)==null){
                service.register(new UserEntity(userEmail,"0",userName,"guest","1",userPhone,1));
                UserDTO member = service.search(userEmail);
                session.setAttribute("member",member);
                return "redirect:/main";
            }else if(service.search(userEmail)!=null){
                UserDTO member = service.search(userEmail);
                session.setAttribute("member",member);
                visitorService.incrementVisitorCount();
                return "redirect:/main";
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println("클래스"+profileResponse.getBody().getClass());
        session.setAttribute("JsonNaver",profileResponse.getBody());

        return "redirect:/main";
    }
    // 응답 본문에서 액세스 토큰 추출을 위한 메서드
    private String extractAccessToken(String responseBody) {
        // JSON 파싱하여 액세스 토큰 추출
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return jsonNode.get("access_token").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("실패", e);
        }
    }
}
