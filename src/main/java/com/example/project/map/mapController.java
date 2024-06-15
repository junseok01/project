package com.example.project.map;

import com.example.project.dto.Gym;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class mapController {
    private final gymService mapService;
    @GetMapping("/map")
    public String mappage(){ return "map/mappage";}
    @GetMapping("/mapMarker")
    @ResponseBody
    public List<Gym> mapMarker(){
        List<Gym> gym = mapService.gymselectlist();
        return gym;
    }
}
