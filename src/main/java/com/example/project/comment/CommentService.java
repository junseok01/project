package com.example.project.comment;

import java.util.List;

public interface CommentService {
    List<CommentResponseDto> findCommentByBoardId(String boardId);
    CommentResponseDto addComment(CommentRequestDTO commentRequestDTO);
    Long deleteComment(Long commentNo);
}
