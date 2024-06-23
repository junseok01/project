package com.example.project.request;

import java.util.List;

public interface RequestDAO {
    void saveRequest(RequestEntity requestEntity);

    List<RequestEntity> showRequest();


    RequestEntity requestEntity(String id);
    void updateToTrainer(String id);
    void deleteById(String id);
    void deleteByKey(Long id);
}
