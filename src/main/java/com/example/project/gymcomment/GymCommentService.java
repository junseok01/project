package com.example.project.gymcomment;

import com.example.project.comment.CommentRequestDTO;
import com.example.project.comment.CommentResponseDto;

import java.util.List;
import java.util.Optional;

public interface GymCommentService {
    List<GymCommentResponseDTO> findCommentByBoardId(String boardId);
    GymCommentResponseDTO addComment(GymCommentRequestDTO commentRequestDTO);
    Long deleteComment(Long commentNo);
}
