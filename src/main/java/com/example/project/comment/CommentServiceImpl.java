package com.example.project.comment;

import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class CommentServiceImpl implements CommentService{
        @Autowired
        private CommentRepository commentRepository;
        @Autowired
        private UserRepository userRepository;
        @Override
    public List<CommentResponseDto> findCommentByBoardId(String boardId){
        return commentRepository.findByBoardId(boardId).stream()
                .map(commentEntity -> new CommentResponseDto(commentEntity.getCommentNo()
                        ,commentEntity.getContent(),commentEntity.getCreatedDate(),commentEntity.getWriter().getLoginId()
                ,commentEntity.getBoardId()))
                .collect(Collectors.toList());
    }

    @Override
    public CommentResponseDto addComment(CommentRequestDTO commentRequestDTO) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(commentRequestDTO.getContent()); // 댓글 내용
        commentEntity.setCreatedDate(LocalDateTime.now()); // 생성 일자

        String writerLoginId = commentRequestDTO.getWriter();
        UserEntity writer = userRepository.findByLoginId(writerLoginId);
        commentEntity.setWriter(writer);// 작성자
        commentEntity.setBoardId(commentRequestDTO.getBoardId()); // board_no
        CommentEntity comment = commentRepository.save(commentEntity);
        return new CommentResponseDto(comment);
    }
    @Override
    public Long deleteComment(Long commentNo) {
        commentRepository.deleteById(commentNo);
        return commentNo;
    }
}
