package com.example.project.trainer;

import org.springframework.data.domain.Page;

import java.util.List;

public interface TrainerService {
    //게시글등록  - tbboard테이블과 board_file테이블에 저장
    /*int insert(TrainerEntity board, List<BoardFileDTO> boardfiledtolist);*/
    void insert(TrainerRequestDTO trainer);
    //게시글목록보기
    List<TrainerResponseDTO> trainerList();
    //게시글상세조회
    // /erp/board/read
    TrainerEntity gettrainerInfo(Long boardNo);
    //게시글수정
    // /erp/board/update
    int update(TrainerEntity board);
    //게시글삭제
    // /erp/board/delete
    void delete(Long boardNo);
    //제목으로 검색
    List<TrainerEntity> search(String data);
    //제목,작성자, 본문, 작성일별로 검색
    List<TrainerEntity> search(String tag,String data);
    //category별로 검색하기
    List<TrainerEntity> findByCategory(String category);
    List<TrainerResponseDTO> pagingFindAll();
    //트레이너 검색
    List<TrainerResponseDTO> findBytrainer(String trainerName);
    List<TrainerResponseDTO>findBytrainer(String trainerName,int page);
    Page<TrainerEntity> getTrainers(int page, int size);
    Page<TrainerEntity> getSearchTrainer(String trainerName,int page, int size);

    //
/*//	//게시글을 상세보기한 경우 보여질 업로드한 파일의 목록 조회
    List<BoardFileDTO> getFileList(String boardno);

    BoardFileDTO getFile(String boardFileno);*/
}
