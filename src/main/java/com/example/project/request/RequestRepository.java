package com.example.project.request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestEntity,Long> {
    void deleteByloginId(String loginId);
    void deleteById(Long id);
}
