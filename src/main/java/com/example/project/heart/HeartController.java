package com.example.project.heart;

import com.example.project.gym.GymBoardEntity;
import com.example.project.login.UserDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HeartController {

    @Autowired
    HeartService heartService;



    @GetMapping("/Heart")
    @ResponseBody
    public ResponseEntity<String> getHeart(@RequestParam("boardNum") String boardNum, HttpSession session){
        System.out.println("넘어온 데이터 " + boardNum);
        Long GymBoardNum = Long.parseLong(boardNum);
        UserDTO user = (UserDTO) session.getAttribute("member");
        String loginId = user.getLoginId();
        List<GymBoardEntity> heartList = heartService.getHeartList(loginId);
        List<Long> heartedGymIds = heartService.getHeartedGymIds(loginId);
        System.out.println(heartedGymIds + "=====================================");

        session.setAttribute("heartList",heartList);
        System.out.println(heartList.contains(1));

        heartService.heartGym(loginId,GymBoardNum);
        session.setAttribute("heartedGymIds",heartedGymIds);
        return ResponseEntity.ok("찜하기 완료");
    }
    @GetMapping("/HeartList")
    public String heartList(HttpSession session, Model model){
        UserDTO userDTO = (UserDTO)session.getAttribute("member");
        String loginId = userDTO.getLoginId();;
        System.out.println(userDTO);
        List<GymBoardEntity> heartList = heartService.getHeartList(loginId);
        model.addAttribute("heartList",heartList);
        return "/test/heartList";
    }
}
