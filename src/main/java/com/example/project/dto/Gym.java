package com.example.project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gym")
public class Gym {
    @Id
    @GeneratedValue
    private int gymNumber;
    private String gymName;
    private String gymTrainer;
    private String gymTel;
    private String gymAddr;
    private int addrNum;
    private float x;
    private float y;
    private String areanum;
    private String businessNum;
    private String memberId;
}

