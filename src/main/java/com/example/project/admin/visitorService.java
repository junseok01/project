package com.example.project.admin;

import java.util.List;

public interface visitorService{
    void incrementVisitorCount();
    int getTodayVisitorCount();
    List<visitorEntity> getAllVisitors();
}
