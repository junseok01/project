package com.example.project.gym;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="gymboard")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GymboardEntity {
    @Id @GeneratedValue
    private Long gymboardnum;
    private String gymname;
    private String gymtrainer;
    private String gymtel;
    private String gymaddr;
    private String info;
    private String monthprice;
    private String yearprice;

    public GymboardEntity(String gymtrainer, String gymtel, String info, String monthprice, String yearprice) {
        this.gymtrainer = gymtrainer;
        this.gymtel = gymtel;
        this.info = info;
        this.monthprice = monthprice;
        this.yearprice = yearprice;
    }
}
