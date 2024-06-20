package com.example.project.board_kim;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.Criteria;
import com.example.project.dto.Paging;
import com.example.project.login.LoginService;
import com.example.project.login.UserDTO;
import com.example.project.login.UserEntity;
import jakarta.servlet.http.HttpSession;
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
    private UserDTO userId;

    @Autowired
    public BoardController(BoardService service) {
        this.service = service;
    }

    @GetMapping("board/myboard")
    public String boardPage(HttpSession session, Model model) {
        // 세션에서 로그인한 사용자 정보 가져오기
        UserDTO userId = (UserDTO) session.getAttribute("member");
        System.out.println("userId ==>"+userId);
        if (userId == null) {
            return "redirect:/login";
        }//새로고침후 테스트용

        // 사용자가 작성한 게시글 리스트 가져오기
        List<BoardDTO> boardlist = service.getByUserId(userId.getLoginId());

        model.addAttribute("boardlist", boardlist);
        model.addAttribute("member", userId);

        return "mypage/myboard";
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
