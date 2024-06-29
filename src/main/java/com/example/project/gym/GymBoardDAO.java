package com.example.project.gym;

import org.springframework.data.domain.Page;

import java.util.List;

public interface GymBoardDAO {
    GymBoardEntity read(Long gymboardnum);
    List<GymBoardEntity> list();
    void delete(Long gymboardnum);
    List<GymBoardEntity> pagingFindAll();
    Page<GymBoardEntity> pagelist(int page, int size);
    List<GymBoardEntity> searchName(String gymname, int page);
    Page<GymBoardEntity> searchGymName(String gymname ,int page,int size);
}
