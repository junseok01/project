package com.example.project.board;

import com.example.project.comment.CommentRequestDTO;
import com.example.project.comment.CommentResponseDto;
import com.example.project.comment.CommentService;
import com.example.project.dto.BoardDTO;
import com.example.project.dto.Criteria;
import com.example.project.dto.Paging;
import com.example.project.login.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {
    private BoardService service;
    private UserDTO userId;
    private CommentService commentService;

    @Autowired
    public BoardController(BoardService service,CommentService commentService) {
        this.service = service;
        this.commentService = commentService;
    }

    @GetMapping("board/myboardpage")
    public String boardPage(HttpSession session, Model model) {
        //세션에서 로그인한 사용자 정보 가져오기
        UserDTO userId = (UserDTO) session.getAttribute("member");
        System.out.println("userId ==>"+userId);
        if (userId == null) {
            return "redirect:/login";
        }//새로고침후 테스트용

        //사용자가 작성한 게시글 리스트 가져오기
        List<BoardDTO> boardlist = service.getByUserId(userId.getLoginId());

        model.addAttribute("boardlist", boardlist);
        model.addAttribute("member", userId);

        return "mypage/myboardpage";
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
        List<CommentResponseDto> comments = commentService.findCommentByBoardId(board_no);
        String view="";
        if(action.equals("READ")) {
            view = "board/board_read";
        }else {
            view = "board/board_update";
        }
        mav.setViewName(view);
        mav.addObject("board",board);
        mav.addObject("comments",comments);
        return mav;
    }
    @PostMapping("/board/comment/add")
    @ResponseBody
    public CommentResponseDto addComment(@RequestBody CommentRequestDTO commentRequestDTO){
        System.out.println(commentRequestDTO);
        return commentService.addComment(commentRequestDTO);
    }
    @DeleteMapping("/board/comment/delete/{commentNo}")
    @ResponseBody
    public Long deleteComment(@PathVariable("commentNo") Long commentNo) {
        System.out.println(commentNo);
        commentService.deleteComment(commentNo);
        return commentNo;
    }
    @GetMapping("board/write")
    public String writepage(HttpSession session, Model model) {
        UserDTO member = (UserDTO) session.getAttribute("member");
        BoardDTO board = new BoardDTO();

        board.setName(member.getName());
        board.setId(member.getLoginId());
        board.setUser_type(member.getUserType());

        model.addAttribute("board", board);
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
