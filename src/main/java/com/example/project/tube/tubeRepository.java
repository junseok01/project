package com.example.project.tube;

import com.example.project.dto.tube;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface tubeRepository extends JpaRepository<tube,Long> {
    List<tube> findByCategory(String category, PageRequest pageRequest);
//    @Query(
//            value = "select * from tube m where m.exercisename=?1 or m.category=?1 or m.tubename=?1",
//            nativeQuery = true
//    )
//    List<tube> findBySearch(String keyword);

    @Query(
                value = "select * from tube m where m.exercisename like '%' || :keyword || '%' or m.category like '%' || :keyword || '%' or m.tubename like '%' || :keyword || '%'",
            nativeQuery = true
    )
    List<tube> findBySearch(String keyword, PageRequest pageRequest);

}

