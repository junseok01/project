package com.example.project.tube;

import com.example.project.dto.tube;
import com.example.project.dto.tubeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class tubeServiceImpl implements tubeService{
    private final tubeDAO dao;
    @Override
    public List<tubeResponseDTO> tubelist() {
        List<tube> list = dao.tubelist();
        ModelMapper mapper = new ModelMapper();
        List<tubeResponseDTO> tubelist = list.stream()
                .map(entity -> mapper.map(entity,tubeResponseDTO.class))
                .collect(Collectors.toList());
        return tubelist;
    }

    @Override
    public List<tubeResponseDTO> tubeselectlist(String keyword) {
        List<tube> selectlist = dao.tubeselectlist(keyword);
        ModelMapper mapper = new ModelMapper();
        List<tubeResponseDTO> tubeselectlist = selectlist.stream()
                .map(entity -> mapper.map(entity,tubeResponseDTO.class))
                .collect(Collectors.toList());
        return tubeselectlist;
    }

    @Override
    public List<tubeResponseDTO> tubecategorylist(String category) {
        List<tube> selectlist = dao.tubecategorylist(category);
        ModelMapper mapper = new ModelMapper();
        List<tubeResponseDTO> tubecategorylist = selectlist.stream()
                .map(entity -> mapper.map(entity,tubeResponseDTO.class))
                .collect(Collectors.toList());
        return tubecategorylist;
    }
}
