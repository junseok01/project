package com.example.project.login;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDAO {
    void insert(UserEntity dto);
    UserDTO search(String id);
    List<UserEntity> findAll();
    void deleteMember(String id);
    void updateUserType2Trainer(String id);
    Page<UserEntity> getUserPage(int page, int  size, Pageable pageable);
    Page<UserEntity> getUserPage(int page, int  size, Pageable pageable,String text,String type);
    void updatePoint(String loginId,int price);
    UserEntity chatsearch(String id);

}
