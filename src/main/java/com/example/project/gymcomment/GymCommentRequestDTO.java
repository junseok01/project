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
public class GymCommentRequestDTO {
    private Long boardno;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private String  writer;
    private int rating;
    private String boardId;

}
