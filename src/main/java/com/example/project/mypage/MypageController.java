package com.example.project.mypage;

import com.example.project.admin.VisitorEntity;
import com.example.project.admin.VisitorService;
import com.example.project.board.BoardServiceImpl;
import com.example.project.dto.BoardDTO;
import com.example.project.login.UserDTO;
import com.example.project.login.UserService;
import com.example.project.trainer.PtDayPasses.PtDayPassesResponseDTO;
import com.example.project.trainer.PtDayPasses.PtDayPassesService;
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
	@Autowired
	VisitorService visitorService;
	@Autowired
	private  PtDayPassesService ptDayPassesService;
	
	@GetMapping("/mypage")
	public String mypage(Model model,HttpSession session){
		UserDTO member = (UserDTO)session.getAttribute("member");
		List<BoardDTO> boardDTOList = boardService.boardList();
		List<UserDTO> userDTOList = userService.findAll();
		List<VisitorEntity> allVisitors = visitorService.getAllVisitors();
		int visitCount=0;
		for(int i=0;i<allVisitors.size();i++){
			visitCount+= allVisitors.get(i).getVisitCount();
		}
		UserDTO trainer = (UserDTO) session.getAttribute("member");
		trainer.setLoginId(trainer.getLoginId());

		List<PtDayPassesResponseDTO> ptDayPasses = ptDayPassesService.getPtDayPassesByTrainer(trainer.getLoginId());
		model.addAttribute("ptDayPasses", ptDayPasses);
		model.addAttribute("reservationTrainerId", trainer.getLoginId());

		List<PtDayPassesResponseDTO> ptDayPassesClient = ptDayPassesService.getAllPtDayPasses();
		model.addAttribute("ptDayPassesClient", ptDayPassesClient);

		System.out.println("전체 보드리스트: " +  boardDTOList);
		System.out.println(member);
		if(member != null){
			model.addAttribute("member",member);
			model.addAttribute("TotalBoard",boardDTOList.size());
			model.addAttribute("TotalUser",userDTOList.size());
			model.addAttribute("TotalVisitor",visitCount);
		}
		return "/mypage/mypage";
	}
}
