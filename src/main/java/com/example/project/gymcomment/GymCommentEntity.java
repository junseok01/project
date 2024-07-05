package com.example.project.gymcomment;

import com.example.project.login.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name="gymcomment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GymCommentEntity {
    @Id
    @GeneratedValue
    private Long commentno;
    private String content;
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "loginId")
    private UserEntity writer; //작성자
    //해당 필드에 최소값,최댓값 설정 및 검증

    private int rating;
    private String boardId;



}
