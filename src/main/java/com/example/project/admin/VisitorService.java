package com.example.project.admin;

import java.time.LocalDate;
import java.util.List;

public interface VisitorService {
    void incrementVisitorCount();
    int getTodayVisitorCount();
    List<VisitorEntity> getAllVisitors();
    List<VisitorEntity> getVisitorsByDateRange(LocalDate startDate, LocalDate endDate);
}
