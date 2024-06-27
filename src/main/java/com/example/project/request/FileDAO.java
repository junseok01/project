package com.example.project.request;

import java.util.List;

public interface FileDAO {
    void save(FileEntity fileEntity);
    List<FileEntity> findByRequest_Id(Long requestId);


}