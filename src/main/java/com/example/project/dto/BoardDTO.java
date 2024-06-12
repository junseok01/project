package com.example.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("board")
public class BoardDTO {
    private String board_no;
    private String id;
    private String name;
    private String title;
    private String content;
    private Date write_date;
    private String category;

}
