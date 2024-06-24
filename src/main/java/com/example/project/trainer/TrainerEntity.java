package com.example.project.trainer;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="trainerboard")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerEntity {
    @Id
    @GeneratedValue
    private Long boardNo;
    private String name;
    private String gymName;
    private String addr;
    private String ticketprice;
    private String career;
    private String info;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private Date writedate;


    public TrainerEntity(String Name, String GymName, String Addr, String ticketprice, String Career,String info) {
        this.name = Name;
        this.gymName= GymName;
        this.addr = Addr;
        this.ticketprice = ticketprice;
        this.career = Career;
        this.info = info;
    }
}
