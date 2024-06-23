package com.example.project.request;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService{
    @Autowired
    RequestDAO requestDAO;
    private final ModelMapper modelMapper;


    public RequestServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public void update(RequestDTO DTO){

    }

    @Override
    public RequestDTO saveRequest(String id, String msg) {
        RequestEntity requestEntity = new RequestEntity(id, msg); // 식별자와 메시지를 설정합니다.
        requestDAO.saveRequest(requestEntity);
        return null;
    }

    @Override
    public List<RequestDTO> showRequest() {
        List<RequestEntity> entities = requestDAO.showRequest();
        List<RequestDTO> requestDTO = entities.stream()
                .map(entity -> modelMapper.map(entity, RequestDTO.class))
                .collect(Collectors.toList());
        return requestDTO;
    }

    @Override
    public void updateToTrainer(String id) {
        requestDAO.updateToTrainer(id);
    }

    @Override
    public void delete(String id) {
        requestDAO.deleteById(id);
    }

    @Override
    public void deleteKey(Long key) {
        requestDAO.deleteByKey(key);
    }
}

