package com.example.project.admin;

import com.example.project.login.UserRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
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
    @Autowired
    private VisitorService visitorService;

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
    @GetMapping("/admin/visitor-stats")
    public List<VisitorEntity> getVisitorStats(@RequestParam("startDate") LocalDate startDate,
                                               @RequestParam("endDate") LocalDate endDate) {
        return visitorService.getVisitorsByDateRange(startDate, endDate);
    }

    @GetMapping("/QR")
    public ResponseEntity<byte[]> qrToTistory() throws WriterException, IOException {
        // QR 정보
        int width = 200;
        int height = 200;
        String url = "http://localhost:8088/gymPayment/success";

        // QR Code - BitMatrix: qr code 정보 생성
        BitMatrix encode = new MultiFormatWriter()
                .encode(url, BarcodeFormat.QR_CODE, width, height);

        // QR Code - Image 생성. : 1회성으로 생성해야 하기 때문에
        // stream으로 Generate(1회성이 아니면 File로 작성 가능.)
        try {
            //output Stream
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            //Bitmatrix, file.format, outputStream
            MatrixToImageWriter.writeToStream(encode, "PNG", out);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(out.toByteArray());

        }catch (Exception e){
            e.getMessage();
        }

        return null;
    }

}
