package com.example.project.map;

import com.example.project.dto.Gym;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class gymDAOImpl implements gymDAO{
    private final EntityManager entityManager;
    @Override
    public List<Gym> gymselectlist() {
        List<Gym> list = entityManager.createQuery("select p from Gym as p", Gym.class).getResultList();
        System.out.println("===========================================================================");
        System.out.println(list);
        return list;
    }
}
