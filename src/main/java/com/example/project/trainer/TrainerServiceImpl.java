package com.example.project.trainer;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TrainerServiceImpl implements TrainerService{
    private final TrainerDAO dao;
    private final TrainerRepository repository;
    @Override
    @Transactional
    public void insert(TrainerRequestDTO trainer,MultipartFile file) throws IOException {
        ModelMapper mapper = new ModelMapper();
        TrainerEntity entity =mapper.map(trainer,TrainerEntity.class);
        dao.insert(entity,file);
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
    public TrainerEntity gettrainerInfo(Long boardNo) {
        TrainerEntity read = dao.read(boardNo);
        return read;
    }

    @Override
    @Transactional
    public void update(Long boardNo,String ticketprice,String career, String info) {
        //예외처리
        TrainerEntity trainer= repository.findById(boardNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid trainer Id:" + boardNo));
        // 더티체킹을 통해 엔티티 수정
        trainer.setTicketprice(ticketprice);
        trainer.setCareer(career);
        trainer.setInfo(info);
        repository.save(trainer);
    }
    @Override
    public void delete(Long boardNo) {
        dao.delete(boardNo);
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

    @Override
    public List<TrainerResponseDTO> findBytrainer(String trainerName) {
        return null;
    }

    public List<TrainerResponseDTO> findBytrainer(String trainerName,int page) {
        List<TrainerEntity> entityList = dao.searchName(trainerName,page);
        ModelMapper mapper = new ModelMapper();
        List<TrainerResponseDTO> trainerlist = entityList.stream()
                .map(entity -> mapper.map(entity,TrainerResponseDTO.class))
                .collect(Collectors.toList());
        return trainerlist;
    }

    @Override
    public Page<TrainerEntity> getTrainers(int page, int size) {
        return dao.pagelist(page, size);
    }

    @Override
    public Page<TrainerEntity> getSearchTrainer(String trainerName, int page, int size) {
        return dao.searchName(trainerName,page,size);
    }

}
