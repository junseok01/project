package com.example.project.board_kim;

import com.example.project.dto.BoardDTO;
import com.example.project.dto.Criteria;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class BoardDAOImpl implements BoardDAO{
    private SqlSession sqlSessionTemplate;
    @Autowired
    public BoardDAOImpl(SqlSession sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public int insert(BoardDTO board) {
        return sqlSessionTemplate.insert("com.multi.project.board.insert",board);
    }

    @Override
    public List<BoardDTO> boardList() {//게시글 리스트
        return sqlSessionTemplate.selectList("com.multi.project.board.selectall");
    }

    @Override
    public List<BoardDTO> getListWithPaging(Criteria cri) {//페이징처리
        return sqlSessionTemplate.selectList("com.multi.project.board.paging",cri);
    }
    @Override
    public int totalCount() {
        return sqlSessionTemplate.selectOne("com.multi.project.board.totalCount");
    }

    @Override
    public BoardDTO read(String board_no) {
        return sqlSessionTemplate.selectOne("com.multi.project.board.read",board_no);
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
