package com.example.project.rating;

import com.example.project.gym.GymBoardEntity;
import com.example.project.login.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rating")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RatingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_seq")
    @SequenceGenerator(name = "rating_seq", sequenceName = "RATING_SEQ", allocationSize = 1)
    private Long id;
    @Column(name = "USERNAME", nullable = false)
    private String username;
    @Column(name = "score", nullable = false)
    private int score;
    @Column(name = "GYM_BOARDNUM", nullable = false)
    private Long gymInfo;
}
