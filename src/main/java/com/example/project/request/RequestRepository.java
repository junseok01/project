package com.example.project.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestRepository extends JpaRepository<RequestEntity,Long> {
    void deleteById(Long id);
    RequestEntity findByLoginId(String loginId);
    @Transactional
    void deleteAllByLoginId(String loginId);
    List<RequestEntity> findAllByState(String state);

    @Modifying
    @Transactional
    @Query("UPDATE RequestEntity SET state = '0' WHERE loginId = :loginId")
    void updateStateByLoginId(String loginId);
    Page<RequestEntity> findByState(String state, Pageable pageable);

}
