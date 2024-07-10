package com.example.project.gym;

import com.example.project.trainer.TrainerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GymBoardRepository extends JpaRepository<GymBoardEntity,Long> {
    Page<GymBoardEntity> findByGymnameContaining(String gymname, Pageable pageRequest);
    Page<GymBoardEntity> findByGymaddrContaining(String gymaddr,Pageable pageRequest);

    GymBoardEntity findByGymboardnum (Long gymboardnum);

    // heartCount가 많은 순으로 상위 10개 항목을 가져오는 쿼리
    // 메서드 이름 기반 쿼리 생성 사용
    List<GymBoardEntity> findTop12ByOrderByHeartCountDesc();

}
