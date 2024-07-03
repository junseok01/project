package com.example.project.gym;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GymBoardResponseDTO {
    private Long gymboardnum;
    private String gymname;
    private String gymtrainer;
    private String gymtel;
    private String gymaddr;
    private String info;
    private String dayprice;
    private String weekprice;
    private String gymphoto;
    private int heartCount = 0;

    public GymBoardResponseDTO(GymBoardEntity gymBoardEntity) {
        this.gymboardnum = gymBoardEntity.getGymboardnum();
        this.gymname = gymBoardEntity.getGymname();
        this.gymtrainer = gymBoardEntity.getGymtrainer();
        this.gymtel = gymBoardEntity.getGymtel();
        this.gymaddr = gymBoardEntity.getGymaddr();
        this.info = gymBoardEntity.getInfo();
        this.dayprice = gymBoardEntity.getDayprice();
        this.weekprice = gymBoardEntity.getWeekprice();
        this.gymphoto = gymBoardEntity.getGymphoto();
    }
}
