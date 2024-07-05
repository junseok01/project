package com.example.project.admin;

import javassist.compiler.ast.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class visitorServiceImpl implements visitorService{
    @Autowired
    private visitorRepository visitorRepository;

    public void incrementVisitorCount() {
        LocalDate today = LocalDate.now();
        visitorEntity visitor = visitorRepository.findByVisitDate(today)
                .orElseGet(() -> new visitorEntity(today, 0));
        visitor.setVisitCount(visitor.getVisitCount() + 1);
        visitorRepository.save(visitor);
    }

    public int getTodayVisitorCount() {
        LocalDate today = LocalDate.now();
        return visitorRepository.findByVisitDate(today)
                .map(visitorEntity::getVisitCount)
                .orElse(0);
    }

    public List<visitorEntity> getAllVisitors() {
        return visitorRepository.findAllByOrderByVisitDateAsc();
    }
}
