package com.example.project.map;



import com.example.project.dto.GymMapResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class mapController {
    private final gymService mapService;
    @GetMapping("/map")
    public String mappage(){ return "map/mappage4";}
    @GetMapping("/mapMarker")
    @ResponseBody
    public List<GymMapResponseDTO> mapMarker(@RequestParam("xvalue") String x,@RequestParam("yvalue") String y){
        List<GymMapResponseDTO> gym = mapService.gymlist(Float.parseFloat(x),Float.parseFloat(y));
        return gym;
    }
    @GetMapping("/mapSearch")
    @ResponseBody
    public List<GymMapResponseDTO> mapsearch(String keyword, Model model,int pageNo){
        List<GymMapResponseDTO> gymselectlist = mapService.gymselectlist(keyword,pageNo);
        return gymselectlist;
    }

}
