package com.example.project.gym;

import com.example.project.trainer.TrainerEntity;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/gymlist")
    public String gympage(Model model ,
                          @RequestParam(value = "gymname",required = false) String gymname,
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "10") int size){
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
    public String gymread(@RequestParam("gymboardnum") Long gymboardnum, @RequestParam("action") String action, Model model){
        GymBoardEntity read = service.getgymInfo(gymboardnum);
        model.addAttribute("gym",read);
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
}
