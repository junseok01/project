package com.example.project.login;


import java.math.BigInteger;
import java.security.SecureRandom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class LoginController {
	@GetMapping("/login")
	public String login() {
		return "login/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("id") String id, @RequestParam("pass") String pass) {
		System.out.println(id);
		System.out.println(pass);
		System.out.println("gkgk");
		return "redirect:/main";
	}

	@GetMapping("/signUp")
	public String signUp() {
		return "register/register";
	}

	@PostMapping("/register")
	public String register(@RequestParam("loginId") String loginId, @RequestParam("loginPw") String loginPw,
			@RequestParam("loginPwConfirm") String loginPwConfirm, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("nickname") String nickname,
			@RequestParam("cellphoneNo") String cellphoneNo, @RequestParam("job") String job) {
		System.out.println("loginId: " + loginId + ", loginPw: " + loginPw +
                ", loginPwConfirm: " + loginPwConfirm + ", name: " + name +
                ", email: " + email + ", nickname: " + nickname +
                ", cellphoneNo: " + cellphoneNo + ", job: " + job);
		
		//비밀번호가 일치하는지 확인
		if(!loginPw.equals(loginPwConfirm)) {
			return "redirect:/signUp";
		}
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
