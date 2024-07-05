package com.example.project.rating;

import com.example.project.gym.GymBoardEntity;
import com.example.project.login.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="rating")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingEntity {
    @Id  @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="loginId")
    private UserEntity userId;

    private int rating;
    @ManyToOne
    @JoinColumn(name="gymboardnum")
    private GymBoardEntity gymInfo;
}
