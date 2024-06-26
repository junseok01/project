package com.example.project.request;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface RequestService {
    RequestDTO searchById(Long id);


    List<RequestDTO> showRequest();

    void deleteByLoginId(String loginId);
    //PK로 들어온 key로 조회하여 삭제
    void deleteById(Long id);
    RequestDTO saveUploadFile(String uploadDirectory, RequestEntity requestEntity,List<MultipartFile> files) throws IOException;


    //트레이너로 업데이트하는 부분 UserService로 옮김
    //void updateToTrainer(String id);
}
