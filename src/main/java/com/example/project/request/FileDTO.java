package com.example.project.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileDTO {
    private Long id;
    private Long requestId;
    private String fileOriginalName;
    private String filepath;
    private String fileName;

}
