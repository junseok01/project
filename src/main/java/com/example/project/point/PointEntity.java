package com.example.project.point;

import com.example.project.login.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "point_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointEntity {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column
    private int amount;

    @Column
    private String description;

    @CreationTimestamp
    private Date transactionDate;
}