package com.example.project.login;

public interface UserDAO {
    public void insert(UserEntity dto);
    public UserDTO search(String id);


}
