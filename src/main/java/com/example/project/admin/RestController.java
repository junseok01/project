package com.example.project.admin;

import com.example.project.login.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.Path;
import java.nio.file.Paths;

@org.springframework.web.bind.annotation.RestController
public class RestController {

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
    @GetMapping("/pdfFileRead")
    public ResponseEntity<Resource> pdfFileRead(@RequestParam("filepath") String filepath) {
        try {

            Path filePath = Paths.get(filepath).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                // 파일을 inline으로 표시하여 브라우저 내에서 열리도록 함
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, "application/pdf")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
