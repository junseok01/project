package com.example.project.mypage;

import com.example.project.board.BoardServiceImpl;
import com.example.project.dto.BoardDTO;
import com.example.project.login.UserDTO;
import com.example.project.login.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MypageController {
	@Autowired
	BoardServiceImpl boardService;
	@Autowired
	UserService userService;

	
	@GetMapping("/mypage")
	public String mypage(Model model,HttpSession session){
		UserDTO member = (UserDTO)session.getAttribute("member");
		List<BoardDTO> boardDTOList = boardService.boardList();
		List<UserDTO> userDTOList = userService.findAll();

		System.out.println("전체 보드리스트: " +  boardDTOList);
		System.out.println(member);
		if(member != null){
			model.addAttribute("member",member);
			model.addAttribute("TotalBoard",boardDTOList.size());
			model.addAttribute("TotalUser",userDTOList.size());
		}
		return "/mypage/mypage";
	}
}
