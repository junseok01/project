package com.example.project.request;

import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RequestDAOImpl implements RequestDAO {
    RequestRepository requestRepository;
    UserRepository userRepository;

    @Autowired
    public RequestDAOImpl(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RequestEntity searchById(Long id) {
        RequestEntity requestEntity = requestRepository.findById(id).get();
        return requestEntity;
    }

    @Override
    public RequestEntity saveRequest(RequestEntity requestEntity) {
        System.out.println("daoImple에서 save엔티티결과" + requestEntity);
        RequestEntity savedEntity = requestRepository.save(requestEntity);
        return savedEntity;
    }

    @Override
    public List<RequestEntity> showRequest() {
        return requestRepository.findAll();
    }

    @Override
    public List<RequestEntity> requestListByState(String state) {
        List<RequestEntity> requestEntityListByState = requestRepository.findAllByState(state);
        return requestEntityListByState;
    }

    @Override
    public RequestEntity requestEntity(String id) {
        return null;
    }



    @Override
    @Transactional
    public void deleteByLoginId(String loginId) {
        System.out.println("dao에서 삭제요청");
       // RequestEntity entity = requestRepository.findByLoginId(loginId);
        requestRepository.deleteAllByLoginId(loginId);
    }


    //
    @Override
    public void deleteById(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public void updateAllState(String loginId) {
        requestRepository.updateStateByLoginId(loginId);
    }

    @Override
    public void updateState(Long id) {
        RequestEntity requestEntity = requestRepository.findById(id).get();
        requestEntity.setState("0");
        requestRepository.save(requestEntity);

    }

    @Override
    public Page<RequestEntity> getRequestPage(int page, int size, Pageable pageable) {
        Page<RequestEntity> requestPage = requestRepository.findAll(pageable);
        return requestPage;
    }

    @Override
    public Page<RequestEntity> getRequestPageByState(int page, int size, String state, Pageable pageable) {
        Page<RequestEntity> reuestPageByState = requestRepository.findByState(state,pageable);
        return reuestPageByState;
    }

}
