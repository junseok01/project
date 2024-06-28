package com.example.project.login;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserDAOImpl implements UserDAO {
    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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

    @Override
    @Transactional
    public void updateUserType2Trainer(String id) {
        UserEntity entity = userRepository.findByLoginId(id);
        entity.setUserType("2");
        userRepository.save(entity);
    }

    @Override
    public Page<UserEntity> getUserPage(int page, int size, Pageable pageable) {
        Page<UserEntity> PagingUser = userRepository.findAll(pageable);
        return PagingUser;
    }
    @Override
    public Page<UserEntity> getUserPage(int page, int size, Pageable pageable,String text,String type) {
        Page<UserEntity> pagingUser=null;
        if(type.equals("loginId")){
            pagingUser = userRepository.findByLoginIdContaining(text,pageable);
        }else if(type.equals("name")) {
            pagingUser = userRepository.findByNameContaining(text,pageable);
        }else{
            pagingUser = userRepository.findByUserTypeContaining(text,pageable);
        }



        return pagingUser;
    }

    @Override
    public void updatePoint(String loginId,int price) {
        UserEntity userEntity = userRepository.findById(loginId).get();
        userEntity.setPoint(price);
    }

}
