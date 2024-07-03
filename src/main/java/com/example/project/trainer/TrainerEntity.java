package com.example.project.trainer;

import com.example.project.trainer.Chat.entity.ChatRoom;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @OneToMany(mappedBy = "trainer")
    @ToString.Exclude
    private List<ChatRoom> roomList = new ArrayList<>();

    public TrainerEntity(String Name, String GymName, String Addr, String ticketprice, String Career,String info) {
        this.name = Name;
        this.gymName= GymName;
        this.addr = Addr;
        this.ticketprice = ticketprice;
        this.career = Career;
        this.info = info;
    }
    public TrainerEntity(String ticketprice,String career,String info){
        this.ticketprice = ticketprice;
        this.career =career;
        this.info = info;
    }
}
