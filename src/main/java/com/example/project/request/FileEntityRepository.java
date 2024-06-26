package com.example.project.request;



import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileEntityRepository extends JpaRepository<FileEntity,Long> {
    List<FileEntity> findByRequest_Id(Long requestId);
}
