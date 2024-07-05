package com.example.project.gymcomment;

import com.example.project.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GymCommentRepository extends JpaRepository<GymCommentEntity,Long> {
    List<GymCommentEntity> findByBoardId(String BoardId);
}
