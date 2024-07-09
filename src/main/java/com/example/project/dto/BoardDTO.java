package com.example.project.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
    private Date write_date;
    private String category;
    private String user_type;
}
