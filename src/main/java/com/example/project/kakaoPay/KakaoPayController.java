package com.example.project.kakaoPay;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoPayController {
    @GetMapping("/kakaopay/success")
    public String success() {
        // 결제 성공 처리 로직
        return "/paymentSuccess"; // 결제 성공 페이지로 리디렉션
    }

    @GetMapping("/kakaopay/fail")
    public String fail() {
        // 결제 실패 처리 로직
        return "/paymentFail"; // 결제 실패 페이지로 리디렉션
    }

    @GetMapping("/kakaopay/cancel")
    public String cancel() {
        // 결제 취소 처리 로직
        return "/paymentCancel"; // 결제 취소 페이지로 리디렉션
    }
}
