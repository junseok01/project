package com.example.project.rating;

import com.example.project.gym.GymBoardEntity;
import com.example.project.gym.GymBoardRepository;
import com.example.project.gym.GymBoardService;
import com.example.project.login.UserDTO;
import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import com.example.project.trainer.TrainerEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;
    private final GymBoardService gymBoardService;

    @GetMapping("/{id}/rating")
    public String rate(){
        return "gym/gymRating";
    }

    @PostMapping("/{id}/rating")
    @ResponseBody
    public String rateGym(@PathVariable("id") Long gymId, @RequestParam("score") int score, @RequestParam("gymboardnum") Long gymBoardNum, Model model,HttpSession session ) {
        UserDTO user = (UserDTO) session.getAttribute("member");
        String username = user.getLoginId();
        System.out.println(gymBoardNum);
        //이미 평점을 부여한 아이디라면 평점부여 x
        if (ratingService.hasUserRated(gymId,username)){
            return "fail";
        }else {
            ratingService.saveRating(gymId,score,username);
            return "success";
        }
    }
}
