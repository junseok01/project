package com.example.project.login;

import java.util.List;

public interface LoginService {
    void register(UserEntity dto);
    UserDTO search(String id);

}
