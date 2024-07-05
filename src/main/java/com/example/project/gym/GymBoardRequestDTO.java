package com.example.project.gym;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GymBoardRequestDTO {
    private String gymtrainer;
    private String gymtel;
    private String info;
    private String dayprice;
    private String weekprice;
}
