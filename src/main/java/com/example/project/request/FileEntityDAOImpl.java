package com.example.project.request;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class FileEntityDAOImpl implements FileDAO {
    private final FileEntityRepository fileEntityRepository;


    @Autowired
    public FileEntityDAOImpl(FileEntityRepository fileEntityRepository) {
        this.fileEntityRepository = fileEntityRepository;
    }


    @Override
    public void save(FileEntity fileEntity) {
        fileEntityRepository.save(fileEntity);
    }

    @Override
    public List<FileEntity> findByRequest_Id(Long requestId) {
        List<FileEntity> fileEntities = fileEntityRepository.findByRequest_Id(requestId);
        return fileEntities;
        //return fileEntityRepository.findAllById(requestId);
    }
}
