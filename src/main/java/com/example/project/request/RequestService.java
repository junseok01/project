package com.example.project.request;

import org.springframework.data.domain.Page;
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
    List<RequestDTO> requestListByState(String state);
    //하나의 요청에 대해서 스테이트 업데이트(관리자가 특정 요청을 삭제함)
    void updateState(Long id);
    //모든 사용자의 스테이트를 업데이트함(관리자가 유저의 등급을 업데이트함)
    void updateAllState(String loginId);
    Page<RequestDTO> getRequestPage(int page,int size);
    Page<RequestDTO> getRequestPageByState(int page,int size,String state);

    //트레이너로 업데이트하는 부분 UserService로 옮김
    //void updateToTrainer(String id);
}
