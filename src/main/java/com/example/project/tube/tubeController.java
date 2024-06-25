package com.example.project.tube;

import com.example.project.dto.tubeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class tubeController {
    private final tubeService tubeService;
    @GetMapping("/tube")
    public String tubepage(Model model){
        List<tubeResponseDTO> tubelist = tubeService.tubelist();
        model.addAttribute("tubelist",tubelist);
        return "tube/tubepage2";
    }
    @GetMapping("/tubecategory")
    public String tubeCategory(@RequestParam("category") String category,Model model){
        List<tubeResponseDTO> tubecategorylist = null;
        if(category.equals("전체")){
             tubecategorylist = tubeService.tubelist();
        }else {
             tubecategorylist = tubeService.tubecategorylist(category);
        }
        model.addAttribute("category",category);
        model.addAttribute("tubelist",tubecategorylist);
        return "tube/tubepage2";
    }
    @PostMapping("/tubesearch")
    public String tubeSelect(@RequestParam("keyword") String keyword, Model model){
        List<tubeResponseDTO> tubeselectlist = tubeService.tubeselectlist(keyword);
        model.addAttribute("tubelist",tubeselectlist);
        System.out.println(keyword);
        return "tube/tubepage2";
    }
}
