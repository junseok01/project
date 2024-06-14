package com.example.project.login;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {
    private final EntityManager entityManager;

    @Transactional
    public void insert(UserEntity dto){entityManager.persist(dto);}

    @Override
    public UserDTO search(String id) {
        UserEntity user= entityManager.find(UserEntity.class,id);
        System.out.println("UserDAOImpl 서치에서 " + user);
        if(user !=null){
            return new UserDTO(user);
        }
        return null;
    }
}
