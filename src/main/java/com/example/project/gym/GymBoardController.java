package com.example.project.gym;

import com.example.project.heart.HeartService;
import com.example.project.login.UserDTO;
import com.example.project.trainer.TrainerEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GymBoardController {
    private final GymBoardService service;
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
            gymBoardEntities = service.getSearchTrainer(gymname,page,size);
        }
        //페이징처리 다음 ,이전

        Page<GymBoardEntity> GymPage = service.getgym(page, size);
        model.addAttribute("GymPage",GymPage);
        model.addAttribute("gymname",gymname);
        model.addAttribute("totalPage",GymPage.getTotalPages());
        model.addAttribute("gymlist",gymBoardEntities);
        return "gym/gymhome";
    }
    @GetMapping("/gymread")
    public String gymread(@RequestParam("gymboardnum") Long gymboardnum, @RequestParam("action") String action, Model model,HttpSession session){
        GymBoardEntity read = service.getgymInfo(gymboardnum);
        model.addAttribute("gym",read);
        session.setAttribute("gyminfo",read);

        String view ="";
        if(action.equals("READ")){
            view = "gym/gym_read";
        }else{
            view = "gym/gym_update";
        }
        return "gym/gym_read";
    }
    @GetMapping("/gymdelete")
    public String delete(String gymboardnum){
        service.delete(Long.parseLong(gymboardnum));
        return "redirect:/gym?category=all";
    }

    //결재페이지로 이동하는 컨트롤러
    @GetMapping("/gymPayment")
    public String payment(@RequestParam("dayPrice") String dayPrice,@RequestParam("weekPrice") String weekPrice){
        System.out.println(dayPrice);
        System.out.println(weekPrice);
        return "/gym/gymPaymentPage";
    }

}
