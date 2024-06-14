package com.example.project.board_kim;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BoardServiceImpl implements BoardService{
    private BoardDAO dao;
    @Autowired
    public BoardServiceImpl(BoardDAO dao) {
        this.dao = dao;
    }
    @Override
    public int insert(BoardDTO board) {
        return 0;
    }

    @Override
    public List<BoardDTO> boardList() {
        List<BoardDTO> boardlist = dao.boardList();
        return boardlist;
    }

    @Override
    public List<BoardDTO> getListWithPaging(Criteria cri) {

        return dao.getListWithPaging(cri);
    }
    @Override
    public int totalCount() {
        return dao.totalCount();
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
