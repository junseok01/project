package com.example.project.admin;

import com.example.project.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin/user-type-count")
    public Map<String, Long> getUserTypeCount() {
        List<Object[]> results = userRepository.countByUserType();
        Map<String, Long> userTypeCount = new HashMap<>();
        for (Object[] result : results) {
            userTypeCount.put((String) result[0], (Long) result[1]);
        }
        return userTypeCount;
    }
}
