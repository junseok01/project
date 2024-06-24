package com.example.project.trainer;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TrainerServiceImpl implements TrainerService{
    private final TrainerDAO dao;
    private final TrainerRepository repository;
    @Override
    public void insert(TrainerRequestDTO trainer ) {
        ModelMapper mapper = new ModelMapper();
        TrainerEntity entity =mapper.map(trainer,TrainerEntity.class);
        dao.insert(entity);
    }
    @Override
    public List<TrainerResponseDTO> trainerList() {
        List<TrainerEntity> entityList =dao.list();
        List<TrainerResponseDTO> trainerlist = entityList.stream()
                .map(TrainerResponseDTO :: new)
                .collect(Collectors.toList());
        return trainerlist;
    }
    @Override
    public TrainerEntity gettrainerInfo(long boardNo) {
        TrainerEntity read = dao.read(boardNo);
        return read;
    }

    @Override
    public int update(TrainerEntity board) {

        return 0;
    }

    @Override
    public int delete(String board_no) {
        return 0;
    }

    @Override
    public List<TrainerEntity> search(String data) {
        return null;
    }

    @Override
    public List<TrainerEntity> search(String tag, String data) {
        return null;
    }

    @Override
    public List<TrainerEntity> findByCategory(String category) {
        return null;
    }

    @Override
    public List<TrainerResponseDTO> pagingFindAll() {
        //dao의 메소드를 호출하고 데이터를 변환
        List<TrainerEntity> entityList = dao.pagingFindAll();
        //step4. 스트림으로 변환(ModelMapper를 이용)
        ModelMapper mapper = new ModelMapper();
        List<TrainerResponseDTO> trainerlist = entityList.stream()
                .map(entity -> mapper.map(entity,TrainerResponseDTO.class))
                .collect(Collectors.toList());
        return trainerlist;
    }

/*
    @Override
    public List<TrainerEntity> findBytrainer(String trainerName) {
        List<TrainerEntity> entity =repository.findByNameContaining(trainerName);
        return entity;
    }
*/

}
