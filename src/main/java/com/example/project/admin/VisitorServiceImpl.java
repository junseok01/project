package com.example.project.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorRepository visitorRepository;

    public void incrementVisitorCount() {
        LocalDate today = LocalDate.now();
        VisitorEntity visitor = visitorRepository.findByVisitDate(today)
                .orElseGet(() -> new VisitorEntity(today, 0));
        visitor.setVisitCount(visitor.getVisitCount() + 1);
        visitorRepository.save(visitor);
    }

    public int getTodayVisitorCount() {
        LocalDate today = LocalDate.now();
        return visitorRepository.findByVisitDate(today)
                .map(VisitorEntity::getVisitCount)
                .orElse(0);
    }

    public List<VisitorEntity> getAllVisitors() {
        return visitorRepository.findAllByOrderByVisitDateAsc();
    }

    public List<VisitorEntity> getVisitorsByDateRange(LocalDate startDate, LocalDate endDate) {
        return visitorRepository.findAllByVisitDateBetween(startDate, endDate);
    }
}
