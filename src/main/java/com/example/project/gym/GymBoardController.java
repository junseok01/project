package com.example.project.gym;

import com.example.project.comment.CommentRequestDTO;
import com.example.project.comment.CommentResponseDto;
import com.example.project.login.UserEntity;
import com.example.project.rating.RatingEntity;
import com.example.project.rating.RatingRepository;
import com.example.project.rating.RatingService;
import com.example.project.heart.HeartService;
import com.example.project.login.UserDTO;
import com.example.project.trainer.TrainerEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    HeartService heartService;
    @GetMapping("/gymlist")
    public String gympage(Model model , HttpSession session,
                          @RequestParam(value = "gymname",required = false) String gymname,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size){
        UserDTO user = (UserDTO)session.getAttribute("member");
        String loginId = user.getLoginId();
        List<Long> heartedGymIds = heartService.getHeartedGymIds(loginId);
        session.setAttribute("heartedGymIds",heartedGymIds);

        Page<GymBoardEntity> gymBoardEntities;
        if(gymname==null){
            gymBoardEntities = service.getgym(page,size);
        }else{
            gymBoardEntities = service.getSearchgymname(gymname,page,size);
        }
        //페이징처리 다음 ,이전
        model.addAttribute("Page",page);
        model.addAttribute("Size",size);
        model.addAttribute("totalPage",gymBoardEntities.getTotalPages());
        model.addAttribute("gymlist",gymBoardEntities);
        model.addAttribute("gymname",gymname);
        return "gym/gymhome";
    }
    @GetMapping("/gymread")
    public String gymread(@RequestParam("gymboardnum") Long gymboardnum, @RequestParam("action") String action, Model model,HttpSession session){
        GymBoardEntity read = service.getgymInfo(gymboardnum);
        System.out.println(gymboardnum);
        model.addAttribute("gym",read);
        session.setAttribute("gyminfo",read);
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

    //결재페이지로 이동하는 컨트롤러
    @GetMapping("/gymPayment")
    public String payment(@RequestParam("dayPrice") String dayPrice,@RequestParam("weekPrice") String weekPrice,@RequestParam("gymboardnum") Long gymBoardNum,
                          Model model){
        GymBoardEntity gymBoardEntity = service.getgymInfo(gymBoardNum);
        model.addAttribute("gym",gymBoardEntity);
        return "/gym/gymPaymentPage";
    }


}
