package com.example.project.point;

import com.example.project.login.UserDTO;

public interface PointService {
    UserDTO buyTicket(int gymPrice, String description, UserDTO user);
    UserDTO addPoint(int addpoint,UserDTO user);
}
