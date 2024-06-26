package com.example.project.comment;

import com.example.project.login.UserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDTO {
    private Long commentNo;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private String writer;
    private String boardId;
}
