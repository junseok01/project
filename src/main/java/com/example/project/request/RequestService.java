package com.example.project.request;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestService {

    //private List<RequestDTO> requests = new ArrayList<>();
    private HashMap<String,RequestDTO> requests = new HashMap<>();

    public void saveRequest(String userId, RequestDTO requestDTO) {
        // 사용자 ID와 요청 내용을 저장하는 로직
        requests.put(userId,requestDTO);

    }

    public HashMap<String,RequestDTO> getAllRequests() {
        return requests;
    }
}

