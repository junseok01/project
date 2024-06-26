package com.example.project.login;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    void deleteMember(String id);
    UserDTO search(String id);
    void updateUserType2Trainer(String id);
}
