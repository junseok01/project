package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class tubeResponseDTO {
    private String exercisename;
    private String category;
    private String tubelink;
    private String tubename;

}
