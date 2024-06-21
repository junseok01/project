package com.example.project.request;

import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RequestDAOImpl implements RequestDAO{
    RequestRepository requestRepository;
    UserRepository userRepository;

    @Autowired
    public RequestDAOImpl(RequestRepository requestRepository, UserRepository userRepository) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveRequest(RequestEntity requestEntity) {
        System.out.println("daoImple에서 save엔티티결과" + requestEntity);
        requestRepository.save(requestEntity);
    }

    @Override
    public List<RequestEntity> showRequest() {
        return requestRepository.findAll();
    }



    @Override
    public RequestEntity requestEntity(String id) {
        return null;
    }

    @Override
    @Transactional
    public void updateToTrainer(String id) {
        System.out.println("여기는 다오임플의 업데이트");
        UserEntity userEntity = userRepository.findById(id).get();
        userEntity.setUserType("2");//트레이너로 변경
        userRepository.save(userEntity);//변경문 디비에 저장
        System.out.println("변경된 것" + userEntity);
    }

    @Override
    @Transactional
    public void deleteById(String loginId) {
        System.out.println("dao에서 삭제요청");
        requestRepository.deleteByloginId(loginId);
    }

    @Override
    public void deleteByKey(Long id) {
        System.out.println(requestRepository.findById(id) + "=========");
        requestRepository.deleteById(id);
    }

}
