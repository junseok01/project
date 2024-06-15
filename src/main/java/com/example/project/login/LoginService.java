package com.example.project.login;

public interface LoginService {
    public void register(UserEntity dto);
    public UserDTO search(String id);
}
