package com.example.project.mypage;

import com.example.project.login.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {
	
	@GetMapping("/mypage")
	public String mypage(Model model,HttpSession session){
		UserDTO member = (UserDTO)session.getAttribute("member");
		System.out.println(member);
		if(member != null){
			model.addAttribute("member",member);
		}
		return "/mypage/mypage";
	}
}
