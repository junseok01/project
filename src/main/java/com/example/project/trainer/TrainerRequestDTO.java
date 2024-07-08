package com.example.project.trainer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerRequestDTO {
    private String trainerId;
    private String Name;
    private String GymName;
    private String Addr;
    private String ticketprice;
    private String Career;
    private String info;
    private String imagename;
    private String imageurl;
}
