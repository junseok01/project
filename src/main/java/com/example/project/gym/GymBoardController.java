package com.example.project.gym;

import com.example.project.comment.CommentRequestDTO;
import com.example.project.comment.CommentResponseDto;
import com.example.project.gymcomment.GymCommentEntity;
import com.example.project.gymcomment.GymCommentRequestDTO;
import com.example.project.gymcomment.GymCommentResponseDTO;
import com.example.project.gymcomment.GymCommentService;
import com.example.project.login.UserEntity;
import com.example.project.rating.RatingEntity;
import com.example.project.rating.RatingRepository;
import com.example.project.rating.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class GymBoardController {
    private final GymBoardService service;
    private final RatingService ratingService;

    @GetMapping("/gymlist")
    public String gympage(Model model ,@RequestParam(value = "gymname" ,required = false) String gymname,
                          @RequestParam(value = "gymaddr" ,required = false) String gymaddr,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size){
        Page<GymBoardEntity> gymlist = null;
        if(gymname==""){
            gymlist = service.getgym(page,size);
        }
        else if(gymname == null){
            gymlist = service.getgym(page,size);
        }else if(gymname!=""){
            gymlist = service.getSearchgymname(gymname,page,size);
        }else if(gymaddr != null && !gymaddr.isEmpty()){
            gymlist =service.getSearchgymaddr(gymaddr,page,size);
        }
        //페이징처리 다음 ,이전
        model.addAttribute("Page",page);
        model.addAttribute("Size",size);
        model.addAttribute("totalPage",gymlist.getTotalPages());
        model.addAttribute("gymlist",gymlist);
        model.addAttribute("gymname",gymname);
        model.addAttribute("gymaddr",gymaddr);
        return "gym/gymhome";
    }
    @GetMapping("/gymread")
    public String gymread(@RequestParam("gymboardnum") Long gymboardnum, @RequestParam("action") String action, Model model){
        GymBoardEntity read = service.getgymInfo(gymboardnum);
        String view ="";
        if(action.equals("READ")){
            view = "gym/gym_read";
        }else{
            view = "gym/gym_update";
        }
        System.out.println(read+"==============================");
        System.out.println(action+"++++++++++++++++++++++++++++++++++++");
        model.addAttribute("gym",read);

        return view;
    }
    @GetMapping("/gymdelete")
    public String delete(String gymboardnum){
        service.delete(Long.parseLong(gymboardnum));
        return "redirect:/gym?category=all";
    }
    @PostMapping("/gymread/{id}/rating")
    public String saveRating(@PathVariable Long id, @RequestParam("score") int score,
                             @AuthenticationPrincipal UserDetails userDetails, Model model) {
        //현재 로그인한 사용자 정보를 가져옴
        UserEntity user = (UserEntity) userDetails;
        RatingEntity rating = new RatingEntity();
        rating.setUserId(user);
        rating.setRating(score);
        GymBoardEntity gym = service.getgymInfo(id);
        rating.setGymInfo(gym);
        ratingService.saveRating(rating);
        System.out.println(rating+"========================================================");
        return "redirect:/gymread?gymboardnum=" + id;
    }
}
