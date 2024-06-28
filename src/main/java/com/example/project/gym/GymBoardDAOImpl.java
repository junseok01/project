package com.example.project.gym;

import com.example.project.trainer.TrainerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class GymBoardDAOImpl implements GymBoardDAO{
    private final GymBoardRepository repository;
    @Override
    public GymBoardEntity read(Long gymboardnum) {
        //값을 넘겨줌
        return repository.findById(gymboardnum).get();
    }

    @Override
    public List<GymBoardEntity> list() {
        return null;
    }

    @Override
    public void delete(Long gymboardnum) {
        GymBoardEntity gymBoardEntity = repository.findById(gymboardnum).get();
        repository.delete(gymBoardEntity);
    }

    @Override
    public List<GymBoardEntity> pagingFindAll() {
        PageRequest pageRequest = PageRequest.of(0,5, Sort.Direction.DESC,"gymboardnum");
        Page<GymBoardEntity> page = repository.findAll(pageRequest);
        System.out.println("*****************************"+page.getTotalPages());
        System.out.println("__________________________________"+ page.getContent());
        return page.getContent();
    }

    @Override
    public Page<GymBoardEntity> pagelist(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);
        return repository.findAll(pageable);
    }

    @Override
    public List<GymBoardEntity> searchName(String gymname, int page) {
        PageRequest pageRequest = PageRequest.of(page,5, Sort.by(Sort.Direction.DESC, "gymboardnum"));
        Page<GymBoardEntity> pagelist = repository.findByGymnameContaining(gymname,pageRequest);
        return pagelist.getContent();
    }
}
