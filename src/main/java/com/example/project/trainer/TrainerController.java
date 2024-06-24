package com.example.project.trainer;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService service;

    @GetMapping("/trainerlist")
    public String trainerpage(Model model , String trainerName) {
        List<TrainerResponseDTO> trainerEntityList = service.trainerList();
        if(trainerName==null){
            trainerEntityList = service.trainerList();
        }else{
            trainerEntityList = service.findBytrainer(trainerName);
        }
        model.addAttribute("trainerlist",trainerEntityList);
        return "trainer/trainerhome";
    }

    @GetMapping("/trainerread")
    public String trainerread(@RequestParam("boardNo") long boardNo, @RequestParam("action") String action, Model model) {
        TrainerEntity read = service.gettrainerInfo(boardNo);
        model.addAttribute("trainer", read);
        String view = "";
        if (action.equals("READ")) {
            view = "trainer/trainer_read";
        } else {
            view = "board/board_update";
        }
        return "trainer/trainer_read";
    }

    @GetMapping("/trainerregister")
    public String registerPage() {
        return "trainer/trainer_register";
    }

    @PostMapping("/trainerregister")
    public String register(TrainerRequestDTO trainer, HttpSession session)
            throws IllegalStateException, IOException {
        System.out.println(trainer);
        service.insert(trainer);
        return "redirect:/trainerlist?category=all";
    }

}
