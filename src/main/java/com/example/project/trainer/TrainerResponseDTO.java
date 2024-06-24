package com.example.project.trainer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerResponseDTO {
    private Long boardNo;
    private String Name;
    private String GymName;
    private String Addr;
    private String ticketprice;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private Date writedate;
    private String Career;
    private String info;
    public TrainerResponseDTO(TrainerEntity trainerEntity) {
        this.boardNo = trainerEntity.getBoardNo();
        this.Name = trainerEntity.getName();
        this.GymName = trainerEntity.getGymName();
        this.Addr = trainerEntity.getAddr();
        this.ticketprice = trainerEntity.getTicketprice();
        this.writedate = trainerEntity.getWritedate();
        this.Career = trainerEntity.getCareer();
        this.info = trainerEntity.getInfo();
    }
}
