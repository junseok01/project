package com.example.project.trainer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class TrainerDAOImpl implements TrainerDAO{
    private final TrainerRepository repository;

    @Override
    public void  insert(TrainerEntity trainer, MultipartFile file) throws IOException {
        //현재 실행중인 디렉토리 위치를 설정 + 파일이 저장될 경로 지정
        String basePath = System.getProperty("user.dir") + "/static/images/trainer";
        // 디렉토리가 존재하는지 확인
        Path path = Paths.get(basePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // 고유한 파일이름 랜덤으로생성
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(basePath, fileName);
        file.transferTo(saveFile);

        // 이미지설정
        trainer.setImagename(fileName);
        trainer.setImageurl("/images/trainer/" + fileName);


        repository.save(trainer);
    }

    @Override
    public TrainerEntity read(String trainerId) {
        return repository.findById(trainerId).get();
    }

    @Override
    public void update(TrainerEntity dto) {
    }

    @Override
    public void delete(String trainerId) {
        //영속성을 받기위해서 boardNo값을 넘겨줌
        TrainerEntity trainerEntity = repository.findById(trainerId).get();
        repository.delete(trainerEntity);
    }

    @Override
    public List<TrainerEntity> list() {
        return repository.findAll();
    }

    @Override
    public List<TrainerEntity> pagingFindAll() {
        PageRequest pageRequest = PageRequest.of(0, 8, Sort.by(Sort.Direction.DESC, "writedate"));
        Page<TrainerEntity> page = repository.findAll(pageRequest);
        System.out.println(page.getContent());
        return page.getContent();
    }

    @Override
    public List<TrainerEntity> searchName(String trainerName) {
        return repository.findByNameContaining(trainerName);
    }

    @Override
    public Page<TrainerEntity> pagelist(int page, int size) {
        Pageable pageable =PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "writedate"));
        return repository.findAll(pageable);
    }

    @Override
    public List<TrainerEntity> searchName(String trainerName,int page) {
        PageRequest pageRequest = PageRequest.of(page,10, Sort.by(Sort.Direction.DESC, "writedate"));
        Page<TrainerEntity> pagelist = repository.findByNameContaining(trainerName,pageRequest);
        System.out.println(pagelist.getContent());
        return pagelist.getContent();
    }

    @Override
    public Page<TrainerEntity> searchName(String trainerName, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"writedate"));
        return repository.findByNameContaining(trainerName,pageRequest);
    }


}
