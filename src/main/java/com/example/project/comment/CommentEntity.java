package com.example.project.comment;

import com.example.project.login.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "mycomment")
@Entity
@Data
public class CommentEntity{
    @Id
    @GeneratedValue
    private Long commentNo;
    private String content; // 댓글 내용
    @CreatedDate
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "loginId")
    private UserEntity writer; // 작성자
    private String boardId;
}
