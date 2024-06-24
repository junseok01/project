package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GymMapResponseDTO {
    private Long gymnumber;
    private String gymname;
    private String gymaddr;
    private String addrnum;
    private String areanum;

    public GymMapResponseDTO(Gym gym){
        this.gymnumber = gym.getGymnumber();
        this.gymname = gym.getGymname();
        this.gymaddr = gym.getGymaddr();
        this.addrnum = gym.getAddrnum();
        this.areanum = gym.getAreanum();
    }


}
