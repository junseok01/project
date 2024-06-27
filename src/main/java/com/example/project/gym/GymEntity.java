package com.example.project.gym;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name="gymboard")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymEntity {
    @Id @GeneratedValue
    private Long gymnumber;
    private String gymname;
    private String gymtrainer;
    private String gymtel;
    private String gymaddr;
    private String info;
    private String addrnum;
    private double x;
    private double y;
    private String areanum;
    private String businessnum;
    private String memberid;

}
