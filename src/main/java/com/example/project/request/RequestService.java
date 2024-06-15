package com.example.project.request;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    private List<RequestDTO> requests = new ArrayList<>();

    public void saveRequest(String userId, RequestDTO requestDTO) {
        // 사용자 ID와 요청 내용을 저장하는 로직
        requests.add(requestDTO);
    }

    public List<RequestDTO> getAllRequests() {
        return requests;
    }
}

