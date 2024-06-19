package com.example.project.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
//@Alias("board")
public class Criteria {
    private int pageNum;//현재페이지
    private int amount;//보여줄리스트갯수
    private String category;

    public Criteria(){//1페이지당 10개씩
        this(1,10);
    }
    public Criteria(int pageNum,int amount){
        this.pageNum=pageNum;
        this.amount=amount;
    }
    public Criteria(int pageNum, int amount, String category) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.category = category;
    }
}
