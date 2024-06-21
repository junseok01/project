package com.example.project.request;


import com.example.project.login.UserDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/request/sendRequest")
    public String sendRequest(@RequestParam("request") String requestContent, HttpSession session,Model model) {
        UserDTO member = (UserDTO)session.getAttribute("member");
        String userId = (String)member.getLoginId();
        requestService.saveRequest(userId, requestContent);

        //requestService.saveRequest(userId, requestDTO);
        System.out.println("요청정보" + requestContent);
        return "redirect:/mypage";
    }

    @GetMapping("/request/viewRequests")
    public String viewRequests(Model model,HttpSession session) {
        List<RequestDTO> requestList = requestService.showRequest();
        System.out.println("반환받은 리스트" + requestList);
        session.setAttribute("RequestList",requestList);

        return "admin/requests";
    }
    @GetMapping("/request/update")
    public String update(@RequestParam("_Id") String _Id,@RequestParam("loginId") String loginId){
        requestService.updateToTrainer(loginId);
        //관리자페이지에서 요청문이 사라지게 해야하므로 삭제진행
        //아래의 delete와 다름
        //loginId로 조회해서 해당아이디로 작성된 요청문 모두삭제(수락을 눌러 업데이트를 했으면 같은 계정으로 작성된 다른 요청문은 볼 필요가 없기에)
        requestService.delete(loginId);
        return "redirect:/request/viewRequests";
    }
    //gerate된 pk ID로 조회하여 삭제
    @GetMapping("/request/delete")
    public String delete(@RequestParam("_id") Long Id){
        //System.out.println("딜리트문 진입" + Id);
        requestService.deleteKey(Id);
        return "redirect:/request/viewRequests";
    }



}
