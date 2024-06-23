package com.example.project.login;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Transactional
    @Override
    public void insert(UserEntity entity){entityManager.persist(entity);}

    @Override
    public UserDTO search(String id) {

        UserEntity user = userRepository.findByLoginId(id);

        if(user !=null){
            System.out.println("--------여기"+user);
            return modelMapper.map(user, UserDTO.class);
        }
        return null;
    }

    @Override
    public List<UserEntity> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities;
    }

    @Override
    public void deleteMember(String id) {
        userRepository.deleteById(id);
    }
}
