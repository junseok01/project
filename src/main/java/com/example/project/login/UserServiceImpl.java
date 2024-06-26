package com.example.project.login;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    UserDAO userDAO;
    ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserDAO dao, ModelMapper modelMapper) {
        this.userDAO = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserEntity> entityList = userDAO.findAll();
        List<UserDTO> UserDTOList = entityList.stream()
                                .map(entity -> modelMapper.map(entity,UserDTO.class))
                                .collect(Collectors.toList());
        return UserDTOList;
    }

    @Override
    public void deleteMember(String id) {
        userDAO.deleteMember(id);
    }

    @Override
    public UserDTO search(String id) {
        UserDTO userDTO = userDAO.search(id);
        System.out.println(userDTO);
        return null;
    }

    //아이디로 유저타입을 트레이너로 변경
    @Override
    public void updateUserType2Trainer(String id) {
        userDAO.updateUserType2Trainer(id);
    }
}
