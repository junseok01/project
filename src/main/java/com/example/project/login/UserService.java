package com.example.project.login;

import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    void deleteMember(String id);
    UserDTO search(String id);
    void updateUserType2Trainer(String id);
    Page<UserDTO> getUserPage(int page, int size);
    Page<UserDTO> getUserPage(int page, int size,String text,String type);
    void updatePoint(String loginId,int price);
}
