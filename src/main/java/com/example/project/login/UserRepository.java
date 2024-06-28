package com.example.project.login;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,String> {
    UserEntity findByLoginId(String loginId);
    @Query("SELECT u.userType, COUNT(u) FROM UserEntity u GROUP BY u.userType")
    List<Object[]> countByUserType();
    Page<UserEntity> findByLoginIdContaining(String searchText, Pageable pageable);
    Page<UserEntity> findByNameContaining(String searchText,Pageable pageable);
    Page<UserEntity> findByUserTypeContaining(String searchText,Pageable pageable);



}
