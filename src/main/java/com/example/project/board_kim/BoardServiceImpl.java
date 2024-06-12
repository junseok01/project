package com.example.project.board_kim;

import com.example.project.dto.BoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardServiceImpl implements BoardService{
    private BoardDAO dao;
    @Override
    public int insert(BoardDTO board) {
        return 0;
    }

    @Override
    public List<BoardDTO> boardList(int pageNo) {
        List<BoardDTO> boardlist = dao.boardList(pageNo);
        return boardlist;
    }

    @Override
    public BoardDTO getBoardInfo(String board_no) {
        return null;
    }

    @Override
    public int update(BoardDTO board) {
        return 0;
    }

    @Override
    public int delete(String boardNo) {
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
