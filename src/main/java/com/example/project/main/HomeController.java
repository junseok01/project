package com.example.project.main;

import com.example.project.board.BoardService;
import com.example.project.dto.BoardDTO;
import com.example.project.dto.tubeResponseDTO;
import com.example.project.gym.GymBoardEntity;
import com.example.project.gym.GymBoardService;
import com.example.project.login.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import com.example.project.tube.tubeService;
@Controller
public class HomeController {
    @Autowired
    GymBoardService gymBoardService;
    @Autowired
    BoardService boardService;
    @Autowired
    tubeService tubeService;

    @GetMapping("/main")
    public String home(HttpSession session) {
        List<GymBoardEntity> top12GymsByHeartCount = gymBoardService.getTop12GymsByHeartCount();
        List<BoardDTO> boardDTOList = boardService.boardList();
        List<tubeResponseDTO> tubelist = tubeService.tubelist(0);
        session.setAttribute("top12GymsByHeartCount",top12GymsByHeartCount);
        session.setAttribute("boardDTOList",boardDTOList);
        session.setAttribute("tubelist",tubelist);
        return "main"; // src/main/resources/templates/index.html
    }
}