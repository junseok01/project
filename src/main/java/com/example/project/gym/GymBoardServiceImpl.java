package com.example.project.gym;

import com.example.project.trainer.TrainerEntity;
import com.example.project.trainer.TrainerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymBoardServiceImpl implements GymBoardService{
    private final GymBoardDAO dao;
    private final GymBoardRepository repository;
    @Override
    public List<GymBoardResponseDTO> gymList() {
        List<GymBoardEntity> list  =dao.list();
        List<GymBoardResponseDTO> gymlist = list.stream()
                .map(GymBoardResponseDTO :: new)
                .collect(Collectors.toList());
        return gymlist;
    }

    @Override
    public GymBoardEntity getgymInfo(Long gymboardnum) {
        GymBoardEntity read = dao.read(gymboardnum);
        return read;
    }

    @Override
    public int update(TrainerEntity board) {
        return 0;
    }

    @Override
    public void delete(Long boardNo) {
        dao.delete(boardNo);
    }

    @Override
    public List<GymBoardResponseDTO> pagingFindAll() {
        //dao메소드를 호출하고 데이터를 변환
        List<GymBoardEntity> list = dao.pagingFindAll();
        System.out.println(list+"servcie");
        // 스트림으로 변환
        ModelMapper mapper = new ModelMapper();
        List<GymBoardResponseDTO> gymlist = list.stream()
                .map(entity -> mapper.map(entity,GymBoardResponseDTO.class))
                .collect(Collectors.toList());
        return gymlist;
    }

    @Override
    public List<GymBoardResponseDTO> findBygym(String gymname, int page) {
        List<GymBoardEntity> entityList = dao.searchName(gymname,page);
        ModelMapper mapper = new ModelMapper();
        List<GymBoardResponseDTO> gymlist = entityList.stream()
                .map(entity -> mapper.map(entity,GymBoardResponseDTO.class))
                .collect(Collectors.toList());
        return gymlist;
    }

    @Override
    public Page<GymBoardEntity> getgym(int page, int size) {
        return dao.pagelist(page,size);
    }

    @Override
    public Page<GymBoardEntity> getSearchTrainer(String gymname, int page, int size) {
        return dao.searchGymName(gymname,page,size);
    }
}
