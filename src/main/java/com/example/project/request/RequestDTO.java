package com.example.project.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private Long id;
    private String loginId;
    private String title;
    private String content;
    private String state;


}
