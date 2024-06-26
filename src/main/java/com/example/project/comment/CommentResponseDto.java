package com.example.project.comment;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponseDto {
    private Long commentNo;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private String writer;
    private String boardId;

    public CommentResponseDto (CommentEntity commentEntity){
        this.commentNo = commentEntity.getCommentNo();
        this.content = commentEntity.getContent();
        this.createdDate = commentEntity.getCreatedDate();
        this.writer = commentEntity.getWriter().getLoginId();
        this.boardId = commentEntity.getBoardId();
    }
}
