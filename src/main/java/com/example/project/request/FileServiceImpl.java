package com.example.project.request;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FileServiceImpl implements FileService {
    @Autowired
    private FileDAO fileDAO;
    private final ModelMapper modelMapper;

    public FileServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public List<FileDTO> searchByRequest_Id(Long requestId) {
        List<FileEntity> fileEntities = fileDAO.findByRequest_Id(requestId);
        List<FileDTO> fileDTOList = fileEntities.stream()
                .map(fileEntity -> modelMapper.map(fileEntity, FileDTO.class))
                .collect(Collectors.toList());
        return fileDTOList;
    }
}
