package com.example.project.dto;

import lombok.Data;

@Data
//@Alias("board")
public class Paging {
    private int startPage;  //시작페이지
    private int endPage;    //끝페이지
    private boolean prev;   //이전페이지
    private boolean next;   //다음페이지
    private int total;  //모든리스트 수
    private Criteria criteria;

    public Paging(int total, Criteria criteria) {
        this.total = total;
        this.criteria = criteria;
        //Math.ceil=올림
        this.endPage = (int) (Math.ceil(criteria.getPageNum()/10.0))*10;
        //현재페이지(1)에 10을 나눈후 나온수 반올림 후 10을 곱한게 끝페이지 , -9하면 시작페이지
        this.startPage = this.endPage -9;

        int lastPage = (int) (Math.ceil((total * 1.0) / criteria.getAmount()));
        //전체리스트total(15)면 한페이지에 보여줄수(10)를 나누고 (1.5) 올림(2), 즉 2가 마지막 페이지

        //마지막페이지가 끝페이지보다 작으면 끝페이지=마지막페이지
        if (lastPage<this.endPage){
            this.endPage = lastPage;
        }

        this.prev = this.startPage>1; //시작페이지가 1보다 크면 이전페이지 존재
        this.next = this.endPage<lastPage;//끝페이지가 마지막페이지보다 작으면 다음페이지 존재

    }
}
