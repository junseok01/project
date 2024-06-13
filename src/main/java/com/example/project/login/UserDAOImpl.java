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
}
