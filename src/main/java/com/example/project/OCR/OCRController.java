package com.example.project.OCR;

import jakarta.persistence.Entity;
import net.sourceforge.tess4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

@Controller
public class OCRController {

    @GetMapping("/ocr/test")
    public String testOCR(Model model) {


        System.out.println("OCR 컨트롤러 진입");
        // OCR 테스트할 이미지 파일 경로
        String imagePath = "src/main/resources/static/images/OCR/자격증2.jpg";
        File image = new File(imagePath);
        System.out.println(image.exists());

        // Tesseract OCR 객체 생성
        ITesseract tesseract = new Tesseract();

        // tessdata 디렉토리 경로 설정
        //String tessdataPath = classLoader.getResource("tessdata").getPath();
        tesseract.setDatapath("src/main/resources/tessdata");

        // 한글 언어 설정
        tesseract.setLanguage("kor");

        try {
            // 이미지 파일을 BufferedImage로 읽어오기
            BufferedImage processedImage = preprocessImage(image);
            //BufferedImage bufferedImage = ImageIO.read(image);

            // OCR 수행
            String result = tesseract.doOCR(processedImage);
            //String result = tesseract.doOCR(bufferedImage);
            model.addAttribute("OCR",result);
            // OCR 결과 출력
            System.out.println("OCR Result:");
            System.out.println(result);

            return "/test/test"; // 지금은 뷰리턴이지만 추후 @RestController로 바꾼후 데이터 리턴(이름,자격증,급수 등등 생각하기)
        } catch (IOException | TesseractException e) {
            e.printStackTrace();
            return "/test/test";
        }
    }

    private static BufferedImage preprocessImage(File imageFile) throws IOException {
        BufferedImage originalImage = ImageIO.read(imageFile);

        // 이미지 크기 조정
        int targetWidth = 500;
        int targetHeight = (int) ((double) originalImage.getHeight() / originalImage.getWidth() * targetWidth);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g.dispose();

        // 이미지 이진화 (임계값 150)
        for (int y = 0; y < resizedImage.getHeight(); y++) {
            for (int x = 0; x < resizedImage.getWidth(); x++) {
                int rgb = resizedImage.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int gValue = (rgb >> 8) & 0xFF;
                int b = rgb & 0xFF;
                int gray = (r + gValue + b) / 3;
                int binary = gray < 150 ? 0xFF000000 : 0xFFFFFFFF;
                resizedImage.setRGB(x, y, binary);
            }
        }

        return resizedImage;
    }

    @GetMapping("/uploadTest")
    public String up(){

        return "/test/uploadTest";
    }

}
