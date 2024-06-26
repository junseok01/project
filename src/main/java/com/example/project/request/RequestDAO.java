package com.example.project.request;

import java.util.List;

public interface RequestDAO {
    RequestEntity searchById(Long id);

    public RequestEntity saveRequest(RequestEntity requestEntity);

    List<RequestEntity> showRequest();


    RequestEntity requestEntity(String id);

    void deleteByLoginId(String id);
    void deleteById(Long id);

    //트레이너로 업데이트하는 작업 UserDAO로 옮김
    //void updateToTrainer(String id);
}
