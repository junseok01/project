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
        return dao.insert(board);
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
    public List<BoardDTO> findByCategoryWithPaging(Criteria cri) {
        return dao.findByCategoryWithPaging(cri);
    }

    @Override
    public int totalCountByCategory(String category) {
        return dao.totalCountByCategory(category);
    }

    @Override
    public BoardDTO getBoardInfo(String board_no) {
        return dao.read(board_no);
    }

    @Override
    public int update(BoardDTO board) {
        return dao.update(board);
    }

    @Override
    public int delete(String boardNo) {
        return dao.delete(boardNo);
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
