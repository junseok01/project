package com.example.project.board_kim;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.Criteria;
import com.example.project.dto.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {
    private BoardService service;
    @Autowired
    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping("board")
    public String boardpage() {
        return "board/board";
    }
    @GetMapping("board/list")
    public String list(@RequestParam("pageNum") int pageNum,
                       @RequestParam(value = "amount", defaultValue = "10") int amount,
            Model model){//게시글목록
        Criteria cri = new Criteria(pageNum,amount);
        List<BoardDTO> boardlist = service.getListWithPaging(cri);
        //총게시글수
        int total = service.totalCount();
        Paging paging = new Paging(total, cri);
        //System.out.println(boardlist+" === boardlist");
        model.addAttribute("boardlist",boardlist);
        model.addAttribute("paging", paging);
        return "board/board";
    }
}
