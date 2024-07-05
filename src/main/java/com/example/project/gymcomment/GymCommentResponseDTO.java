package com.example.project.gymcomment;

import com.example.project.login.UserEntity;
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
public class GymCommentResponseDTO {
    private Long commentno;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private String user;
    private int rating;
    private String BoardId;

    public GymCommentResponseDTO(GymCommentEntity entity) {
        this.commentno = entity.getCommentno();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
        this.user = entity.getWriter().getLoginId();
        this.rating = entity.getRating();
        this.BoardId = entity.getBoardId();
    }

}
