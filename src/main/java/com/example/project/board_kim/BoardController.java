package com.example.project.board_kim;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.Criteria;
import com.example.project.dto.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
                       @RequestParam(value = "category", defaultValue = "all") String category,
            Model model){
        Criteria cri = new Criteria(pageNum,amount,category);
        List<BoardDTO> boardlist;
        int total;//총게시글수
        if ("all".equals(category)) {
            boardlist = service.getListWithPaging(cri);
            total = service.totalCount();
        } else {
            boardlist = service.findByCategoryWithPaging(cri);
            total = service.totalCountByCategory(category);
        }
        Paging paging = new Paging(total, cri);
        model.addAttribute("boardlist",boardlist);
        model.addAttribute("paging", paging);
        model.addAttribute("category", category);
        return "board/board";
    }
    @GetMapping("board/read")
    public ModelAndView read(@RequestParam("board_no") String board_no, @RequestParam("action") String action) {
       // System.out.println(board_no+"==board_no"+action+"==action");
        ModelAndView mav = new ModelAndView();
        BoardDTO board = service.getBoardInfo(board_no);
        String view="";
        if(action.equals("READ")) {
            view = "board/board_read";
        }else {
            view = "board/board_update";
        }
        mav.setViewName(view);
        mav.addObject("board",board);
        return mav;
    }
    @GetMapping("board/write")
    public String writepage() {
        return "board/board_write";
    }
    @PostMapping("board/write")
    public String insert(BoardDTO board){
        service.insert(board);
        return "redirect:/board/list?pageNum=1&amount=10&category=all";
    }
    @PostMapping("board/update")
    public String update(BoardDTO board) {
        service.update(board);
        return "redirect:/board/list?pageNum=1&amount=10&category=all";
    }
    @GetMapping("board/delete")
    public String delete(String board_no) {
        service.delete(board_no);
        return "redirect:/board/list?pageNum=1&amount=10&category=all";
    }
}
