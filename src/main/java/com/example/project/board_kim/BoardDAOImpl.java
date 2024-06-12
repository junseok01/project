package com.example.project.board_kim;

import com.example.project.dto.BoardDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BoardDAOImpl implements BoardDAO{
    private SqlSession sqlSessionTemplate;
    @Override
    public int insert(BoardDTO board) {
        return 0;
    }

    @Override
    public List<BoardDTO> boardList(int pageNo) {//게시글 리스트(페이징처리)

        return null;
    }

    @Override
    public BoardDTO read(String board_no) {
        return null;
    }

    @Override
    public int update(BoardDTO board) {
        return 0;
    }

    @Override
    public int delete(String board_no) {
        return 0;
    }

    @Override
    public List<BoardDTO> search(String data) {
        return List.of();
    }

    @Override
    public List<BoardDTO> search(String tag, String data) {
        return List.of();
    }

    @Override
    public List<BoardDTO> findByCategory(String category) {
        return List.of();
    }
}
