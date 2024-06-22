package com.example.project.request;

import java.util.List;

public interface RequestService {
    RequestDTO saveRequest(String id,String msg);
    List<RequestDTO> showRequest();
    void updateToTrainer(String id);
    void delete(String id);
    //PK로 들어온 key로 조회하여 삭제
    void deleteKey(Long key);
}
