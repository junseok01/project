package com.example.project.request;

import java.util.List;

public interface FileService {
    List<FileDTO> searchByRequest_Id(Long requestId);
}
