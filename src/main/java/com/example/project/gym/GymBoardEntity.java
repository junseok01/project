package com.example.project.gym;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="gymboard")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymBoardEntity {
    @Id @GeneratedValue
    private Long gymboardnum;
    private String gymname;
    private String gymtrainer;
    private String gymtel;
    private String gymaddr;
    private String info;
    private String dayprice;
    private String weekprice;
    private String gymphoto;

    @Column
    private int heartCount = 0;

    public void incrementHeartCount() {
        this.heartCount++;
    }

    public void decrementHeartCount() {
        this.heartCount--;
    }

    public GymBoardEntity(String gymtrainer, String gymtel, String info, String dayprice, String weekprice) {
        this.gymtrainer = gymtrainer;
        this.gymtel = gymtel;
        this.info = info;
        this.dayprice = dayprice;
        this.weekprice = weekprice;
    }

}
