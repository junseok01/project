package com.example.project.login;

import java.util.List;

public interface UserDAO {
    void insert(UserEntity dto);
    UserDTO search(String id);
    List<UserEntity> findAll();
    void deleteMember(String id);


}
