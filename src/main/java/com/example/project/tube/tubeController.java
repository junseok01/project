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
        List<tubeResponseDTO> tubelist = tubeService.tubelist(0);
        model.addAttribute("tubelist",tubelist);
        model.addAttribute("pageNo",0);
        return "tube/tubepage2";
    }
    @GetMapping("/tubepaging")
    public String tubepaging(Model model, String pageNo,String category,String keyword){
        int pagingNo = Integer.parseInt(pageNo);
        List<tubeResponseDTO> tubelist;
        if(pagingNo<0){
             pagingNo=0;
        }else if(pagingNo>4){
            pagingNo = 4;
        }
        if (!category.equals("전체") && !category.isEmpty() && category !=null) {
             tubelist = tubeService.tubecategorylist(category,pagingNo);
             model.addAttribute("category",category);
        }else if (keyword !=null && !keyword.isEmpty()){
            tubelist = tubeService.tubeselectlist(keyword,pagingNo);
            model.addAttribute("keyword",keyword);
        }else {
            tubelist = tubeService.tubelist(pagingNo);
        }

        model.addAttribute("tubelist",tubelist);
        model.addAttribute("pageNo",pageNo);
        return "tube/tubepage2";
    }
    @GetMapping("/tubecategory")
    public String tubeCategory(@RequestParam("category") String category,Model model){
        List<tubeResponseDTO> tubecategorylist = null;
        if(category.equals("전체")){
             tubecategorylist = tubeService.tubelist(0);
        }else {
             tubecategorylist = tubeService.tubecategorylist(category,0);
        }
        model.addAttribute("category",category);
        model.addAttribute("tubelist",tubecategorylist);
        model.addAttribute("pageNo",0);
        return "tube/tubepage2";
    }
    @PostMapping("/tubesearch")
    public String tubeSelect(@RequestParam("keyword") String keyword, Model model){
        System.out.println(keyword);
        List<tubeResponseDTO> tubeselectlist = tubeService.tubeselectlist(keyword,0);
        model.addAttribute("tubelist",tubeselectlist);
        model.addAttribute("keyword",keyword);
        model.addAttribute("pageNo",0);
        System.out.println(keyword);
        return "tube/tubepage2";
    }
}
