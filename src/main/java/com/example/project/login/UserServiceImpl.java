package com.example.project.login;

import com.example.project.gym.GymBoardEntity;
import com.example.project.gym.GymBoardRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    UserDAO userDAO;
    ModelMapper modelMapper;
    GymBoardRepository gymBoardRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, ModelMapper modelMapper, GymBoardRepository gymBoardRepository, UserRepository userRepository) {
        this.userDAO = userDAO;
        this.modelMapper = modelMapper;
        this.gymBoardRepository = gymBoardRepository;
        this.userRepository = userRepository;
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



    @Override
    public Page<UserDTO> getUserPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<UserEntity> userEntityPage  =userDAO.getUserPage(page,size,pageable);
        List<UserDTO> userDTOList = userEntityPage
                .getContent()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(userDTOList, pageable, userEntityPage.getTotalElements());
    }

    @Override
    public Page<UserDTO> getUserPage(int page, int size,String text,String type) {
        Pageable pageable = PageRequest.of(page,size);
        Page<UserEntity> userEntityPage  = userDAO.getUserPage(page,size,pageable,text,type);
        List<UserDTO> userDTOList = userEntityPage
                .getContent()
                .stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDTO.class))
                .collect(Collectors.toList());

        return new PageImpl<>(userDTOList, pageable, userEntityPage.getTotalElements());
    }
    @Override
    public void updatePoint(String loginId,int price) {
        userDAO.updatePoint(loginId,price);
    }


}
