package com.example.project.login;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService{
    UserDAO dao;

    @Autowired
    public LoginServiceImpl(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    public void register(UserEntity dto) {
        dao.insert(dto);
    }

    @Override
    public UserDTO search(String id) {
        return dao.search(id);
    }



}
