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
    private Long gymnumber;
    private String gymname;
    private String gymtel;
    private String gymaddr;
    private String addrnum;
    private float x;
    private float y;
    private int dailyprice;
}

