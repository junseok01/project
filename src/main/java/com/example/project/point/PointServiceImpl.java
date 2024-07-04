package com.example.project.point;

import com.example.project.login.UserDTO;
import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PointServiceImpl implements PointService{


    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PointRepository pointRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDTO buyTicket(int gymPrice, String description, UserDTO user) {
        user.setPoint(user.getPoint()-gymPrice);
        UserEntity userEntity = modelMapper.map(user,UserEntity.class);
        userRepository.save(userEntity);

        PointEntity point=new PointEntity();
        point.setAmount(-gymPrice);
        point.setDescription(description);
        point.setUser(userEntity);
        pointRepository.save(point);

        return user;
    }

    @Override
    @Transactional
    public UserDTO addPoint(int addpoint, UserDTO user) {
        user.setPoint(user.getPoint()+addpoint);
        UserEntity userEntity = modelMapper.map(user,UserEntity.class);
        userRepository.save(userEntity);

        PointEntity point=new PointEntity();
        point.setAmount(addpoint);
        point.setDescription("포인트충전");
        point.setUser(userEntity);
        pointRepository.save(point);

        return user;
    }
}
