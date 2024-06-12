package com.example.project.board_kim;

import com.example.project.dto.BoardDTO;

import java.util.List;

public interface BoardDAO {
    int insert(BoardDTO board);
    //게시글목록보기 - db에 처리
    List<BoardDTO> boardList(int pageNo);
    //게시글상세조회 - db에 처리
    BoardDTO read(String board_no);
    //게시글수정 - db에 처리
    int update(BoardDTO board);
    //게시글삭제 - db에 처리
    int delete(String board_no);
    //제목으로 검색
    List<BoardDTO> search(String data);
    //제목,작성자, 본문, 작성일별로 검색
    List<BoardDTO> search(String tag,String data);
    //category별로 검색
    List<BoardDTO> findByCategory(String category);
}
