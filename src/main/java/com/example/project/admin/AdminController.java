package com.example.project.admin;

import com.example.project.login.UserDTO;
import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import com.example.project.login.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/memberList")
    public String memberList(@RequestParam(value = "page" ,defaultValue = "0") int page
                            ,@RequestParam(value = "size",defaultValue = "11") int size
                            ,@RequestParam(value = "searchText", required = false) String text
                            ,@RequestParam(value = "searchType",required = false) String type
                            ,Model model,HttpSession session){
        String searchText=null;
        String searchType=null;

        Page<UserDTO> userPage = null;

        //@PostMapping("/admin/memberList/search")여기서 받은 데이터를 session에 저장해서 꺼내오는작업
        //데이터를 가져왔다면 바로 세션삭제 - 삭제하지 않으면 다른링크로 접속을 하더라도 계속 세션에서 데이터를 뽑아와 원치 않는 결과를 추출함
        HashMap<String, String> map = (HashMap<String, String>) session.getAttribute("searchingMember");
        if(map !=null) {
            searchText = map.get("searchText");
            searchType = map.get("searchType");
        }
        session.removeAttribute("searchingMember");

        //<a>링크를통해 타입을 전달받았을떄 값을 저장하는부분
        if(text!=null || type!=null){
            searchText = text;
            searchType = type;
        }

        //조건을 판단후 유저전체 데이터를 가저올지 혹은 사용자가 검색한 것에 맞춘 데이터를 가저올지
        if(searchText == null && searchType==null){
            userPage = userService.getUserPage(page,size);
        }else{
            userPage = userService.getUserPage(page,size,searchText,searchType);
        }
        model.addAttribute("memberList",userPage.getContent());
        model.addAttribute("currentPage",userPage.getNumber());
        model.addAttribute("totalPages",userPage.getTotalPages());
        model.addAttribute("searchText", searchText);
        model.addAttribute("searchType", searchType);

        return "/admin/memberList";
    }
    @PostMapping("/admin/memberList/search")
    public String viewMemberListSearch(@RequestParam("searchText") String searchText,
                                     @RequestParam("searchType") String searchType,HttpSession session) {
        HashMap<String,String> map = new HashMap<>();
        map.put("searchText",searchText);
        map.put("searchType",searchType);
        session.setAttribute("searchingMember",map);

        return "redirect:/admin/memberList";
    }


    @GetMapping("/admin/deleteMember")
    public String deleteMember(@RequestParam("id") String id){
        userService.deleteMember(id);
        return "redirect:/admin/memberList";
    }

    @GetMapping("/page/list")
    public String listUsers(Model model,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,HttpSession session) {


        Page<UserDTO> userDTOList = userService.getUserPage(page,size);
        System.out.println(userDTOList);
        System.out.println(userDTOList.getContent());
        System.out.println(userDTOList.getNumber());
        System.out.println(userDTOList.getTotalPages());
        System.out.println(userDTOList.getSize());
        System.out.println(userDTOList.getTotalElements());
        System.out.println(userDTOList.getNumberOfElements());

        System.out.println("---------------------------------------");
        List<UserDTO> UserList = userService.findAll();
        session.setAttribute("memberList",UserList);

        System.out.println(userDTOList.getContent());
        System.out.println(userDTOList.getNumber());
        System.out.println(userDTOList.getTotalPages());
        model.addAttribute("users", userDTOList.getContent());
        model.addAttribute("currentPage", userDTOList.getNumber());
        model.addAttribute("totalPages", userDTOList.getTotalPages());

        return "/test/page";
    }
}
