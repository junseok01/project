package com.example.project.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RequestDAO {
    RequestEntity searchById(Long id);

    public RequestEntity saveRequest(RequestEntity requestEntity);

    List<RequestEntity> showRequest();
    List<RequestEntity> requestListByState(String state);

    RequestEntity requestEntity(String id);
    void deleteByLoginId(String id);
    void deleteById(Long id);
    void updateAllState(String loginId);
    void updateState(Long id);
    Page<RequestEntity> getRequestPage(int page, int size, Pageable pageable);
    Page<RequestEntity> getRequestPageByState(int page,int size,String state,Pageable pageable);

    //트레이너로 업데이트하는 작업 UserDAO로 옮김
    //void updateToTrainer(String id);
}
