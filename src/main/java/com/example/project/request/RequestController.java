package com.example.project.request;


import com.example.project.OCR.OCR;
import com.example.project.login.UserDTO;
import com.example.project.login.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class RequestController {
    private final RequestService requestService;
    private final FileService fileService;
    private final UserService userService;
    private final String uploadDirectory = "src/main/resources/static/OCRPDF/";

    @Autowired
    public RequestController(RequestService requestService, FileService fileService,UserService userService) {
        this.requestService = requestService;
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping("/request/viewRequests")
    public String viewRequests(HttpSession session) {
        List<RequestDTO> requestDTOList = requestService.showRequest();
        //List<RequestDTO> requestList = requestService.showRequest();
        //session.setAttribute("RequestList",requestList);
        System.out.println("반환받은 리스트" + requestDTOList);
        session.setAttribute("RequestList", requestDTOList);

        return "admin/requests";
    }

    @GetMapping("/request/update")
    public String update(@RequestParam("loginId") String loginId) {
        userService.updateUserType2Trainer(loginId);
        //requestService.updateToTrainer(loginId);
        //관리자페이지에서 요청문이 사라지게 해야하므로 삭제진행
        //아래의 delete와 다름
        //loginId로 조회해서 해당아이디로 작성된 요청문 모두삭제(수락을 눌러 업데이트를 했으면 같은 계정으로 작성된 다른 요청문은 볼 필요가 없기에)
        requestService.deleteByLoginId(loginId);
        return "redirect:/request/viewRequests";
    }

    //generate된 pk ID로 조회하여 삭제
    @GetMapping("/request/delete")
    public String delete(@RequestParam("id") Long Id) {
        //System.out.println("딜리트문 진입" + Id);
        requestService.deleteById(Id);
        return "redirect:/request/viewRequests";
    }

    @GetMapping("/RequestToTrainer")
    public String RequestToTrainer(@RequestParam("action") String action, @RequestParam("id") String str, Model model) {
        Long id = Long.parseLong(str);
        //일반유저가 트레이너로 요청작성을 하기위해 보여주는 페이지
        System.out.println(action + "_____________________________");
        if (action.equals("WRITE")) {
            System.out.println(id);
            return "/request/requestview";
        } else { //관리자가 요청페이지를 확인하기 위한 뷰
            RequestDTO requestDTO = requestService.searchById(id);
            List<FileDTO> fileDTOList = fileService.searchByRequest_Id(id);


            System.out.println(requestDTO);
            System.out.println(fileDTOList);
            model.addAttribute("requestDTO", requestDTO);
            model.addAttribute("fileDTOList", fileDTOList);
            return "/request/requestview_READ";
        }
    }

    @PostMapping("/RequestToTrainer")
    public String Request2Trainer(RequestDTO requestDTO, List<MultipartFile> files) throws IOException {
        //요청사용자 계정은 요청페이지에서 hidden으로 보냄, 나머지는 입력
        RequestEntity requestEntity = new RequestEntity(requestDTO);
        System.out.println(requestEntity);
        System.out.println(files);
        System.out.println("----------------------------");
        //List<MultipartFile> files = requestDTO.getFiles();
        requestDTO = requestService.saveUploadFile(uploadDirectory, requestEntity, files);

        //
        //이곳에서 OCR로 읽어서 적합한지 아닌지 판단후 맞으면 업데이트 적합하지 않거나 읽지 못했을 경우 관리자한테 넘기는 작업 수행
        System.out.println(requestDTO.getId());
        List<FileDTO> fileDTOList = fileService.searchByRequest_Id(requestDTO.getId());
        OCR ocr = new OCR();
        List<String> imgPathList = ocr.convertPdfToImage(fileDTOList);
        for(String imagePath : imgPathList){
            String result = ocr.imgToTxt(imagePath);
            if(!result.equals("요건충족")){
                System.out.println("요건이 불충족 되었습니다. 관리자 승인을 기다리세요");
                return "main";
            }

        }

        System.out.println("모든요건이 충족되었습니다 유저 등급을 트레이너로 업데이트합니다");
        return "redirect:/request/update?loginId=" + requestDTO.getLoginId();
        //모두 요건이 충족이 되었을경우 유저등급 업데이트


    }


}
