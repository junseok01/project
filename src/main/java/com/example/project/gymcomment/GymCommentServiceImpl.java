package com.example.project.gymcomment;

import com.example.project.login.UserEntity;
import com.example.project.login.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymCommentServiceImpl implements GymCommentService{
    private GymCommentRepository repository;
    private UserRepository userRepository;
    @Override
    public List<GymCommentResponseDTO> findCommentByBoardId(String boardId){
        return repository.findByBoardId(boardId).stream()
                .map(gymCommentEntity -> new GymCommentResponseDTO(gymCommentEntity.getCommentno()
                        , gymCommentEntity.getContent(), gymCommentEntity.getCreatedDate(), gymCommentEntity.getWriter().getLoginId(), gymCommentEntity.getRating()
                        , gymCommentEntity.getBoardId()))
                .collect(Collectors.toList());
    }

    @Override
    public GymCommentResponseDTO addComment(GymCommentRequestDTO requestDTO) {
        GymCommentEntity commentEntity = new GymCommentEntity();
        commentEntity.setContent(requestDTO.getContent()); // 댓글 내용
        commentEntity.setCreatedDate(LocalDateTime.now()); // 생성 일자

        String writerLoginId = requestDTO.getWriter();
        UserEntity writer = userRepository.findByLoginId(writerLoginId);
        commentEntity.setWriter(writer);// 작성자
        commentEntity.setBoardId(requestDTO.getBoardId()); // board_no
        GymCommentEntity comment = repository.save(commentEntity);
        return new GymCommentResponseDTO(comment);
    }
    @Override
    public Long deleteComment(Long commentNo) {
        repository.deleteById(commentNo);
        return commentNo;
    }

}
