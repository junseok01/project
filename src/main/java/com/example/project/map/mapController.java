package com.example.project.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class mapController {
    @GetMapping("/map")
    public String mappage(){ return "map/mappage";}
}
