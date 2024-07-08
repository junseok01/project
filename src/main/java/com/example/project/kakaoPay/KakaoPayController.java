package com.example.project.kakaoPay;


import com.example.project.login.UserDTO;
import com.example.project.point.PointController;
import com.example.project.point.PointService;
import com.example.project.point.PointServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class KakaoPayController {
    @Autowired
    PointService pointService;

    @GetMapping("/kakaopay/success")
    public String success(@RequestParam("pg_token") String pgToken,HttpSession httpSession) {
        // 결제 승인 처리 로직
        String tid = (String)httpSession.getAttribute("tid"); // 실제 결제 준비 단계에서 저장한 TID 값을 사용해야 함
        UserDTO user = (UserDTO) httpSession.getAttribute("member");
        String loginId= user.getLoginId();

        // 결제 승인 요청
        String approvalUrl = "https://open-api.kakaopay.com/online/v1/payment/approve";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "DEV_SECRET_KEY " + "DEV3EFB35F02021676106388E99F4AABBED5ECE0");

        Map<String, String> params = new HashMap<>();
        params.put("cid", "TC0ONETIME");
        params.put("tid", tid);
        params.put("partner_order_id", "포인트");
        params.put("partner_user_id", loginId);
        params.put("pg_token", pgToken);

        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(params, headers);
        ResponseEntity<String> response = restTemplate.exchange(approvalUrl, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            PointController pointController = new PointController();
            int amount = (int)httpSession.getAttribute("amount");
            UserDTO userDTO = pointService.addPoint(amount, user);
            httpSession.setAttribute("member",userDTO);

            return "/kakaoPay/paymentSuccess"; // 결제 성공 페이지로 리디렉션
        } else {
            System.err.println("결제 승인 실패: " + response.getStatusCode());
            return "/kakaoPay/paymentFail"; // 결제 실패 페이지로 리디렉션
        }
    }

    @GetMapping("/kakaopay/fail")
    public String fail() {
        // 결제 실패 처리 로직
        return "/kakaoPay/paymentFail"; // 결제 실패 페이지로 리디렉션
    }

    @GetMapping("/kakaopay/cancel")
    public String cancel() {
        // 결제 취소 처리 로직
        return "/kakaoPay/paymentCancel"; // 결제 취소 페이지로 리디렉션
    }

    @GetMapping("/kakaoPay/rechargePoint")
    public void rechargePoint(@RequestParam("amount") int amount, HttpServletResponse httpServletResponse, HttpSession httpSession) {
        // Set your secret key
        String secretKey = "DEV3EFB35F02021676106388E99F4AABBED5ECE0";

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "DEV_SECRET_KEY " + secretKey);

        UserDTO user = (UserDTO) httpSession.getAttribute("member");
        String loginId= user.getLoginId();

        // Create request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("cid", "TC0ONETIME");
        requestBody.put("partner_order_id", "포인트");
        requestBody.put("partner_user_id", loginId);
        requestBody.put("item_name", "포인트 충전");
        requestBody.put("quantity", "1");
        requestBody.put("total_amount", amount);
        requestBody.put("tax_free_amount", "0");
        requestBody.put("approval_url", "http://127.0.0.1:8088/kakaopay/success");
        requestBody.put("fail_url", "http://127.0.0.1:8088/kakaopay/fail");
        requestBody.put("cancel_url", "http://127.0.0.1:8088/kakaopay/cancel");

        // Create HttpEntity
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Send the request
        String url = "https://open-api.kakaopay.com/online/v1/payment/ready";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // Print the response
        System.out.println(response.getBody());
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(response.getBody());

            String nextRedirectPcUrl = jsonNode.get("next_redirect_pc_url").asText();
            String tid=jsonNode.get("tid").asText();
            httpSession.setAttribute("tid",tid);
            httpSession.setAttribute("amount",amount);
            httpServletResponse.sendRedirect(nextRedirectPcUrl);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
