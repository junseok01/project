package com.example.project.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RequestRepository extends JpaRepository<RequestEntity,Long> {
    void deleteById(Long id);
    RequestEntity findByLoginId(String loginId);
    @Transactional
    void deleteAllByLoginId(String loginId);

}
