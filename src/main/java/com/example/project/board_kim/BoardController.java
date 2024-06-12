package com.example.project.board_kim;

import com.example.project.dto.BoardDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {
    private BoardService service;
    @GetMapping("/templates/board")
    public String boardpage() {
        return "templates/board/board";
    }
    @GetMapping("/templates/board/list")
    //페이지 처리 리스트
    public String list(@RequestParam("pageNo") String pageNo, Model model){
        List<BoardDTO> pagelist = service.boardList(Integer.parseInt(pageNo));
        model.addAttribute("pagelist",pagelist);
        return "templates/board/board";
    }
}
