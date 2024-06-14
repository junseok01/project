package com.example.project.board_kim;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.Criteria;

import java.util.List;

public interface BoardService {
    //게시글등록
    int insert(BoardDTO board);
    //게시글목록보기
    List<BoardDTO> boardList();
    //페이징 처리
    List<BoardDTO> getListWithPaging(Criteria cri);
    //게시글상세조회
    BoardDTO getBoardInfo(String board_no);
    //게시글수정
    int update(BoardDTO board);
    //게시글삭제
    int delete(String boardNo);
    //제목으로 검색
    List<BoardDTO> search(String data);
    //제목,작성자, 본문, 작성일별로 검색
    List<BoardDTO> search(String tag,String data);
    //category별로 검색하기
    List<BoardDTO> findByCategory(String category);
    //게시글 총갯수
    int totalCount();
}
