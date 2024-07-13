package com.example.project.OCR;

import com.example.project.request.FileDTO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OCR {
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

    public String imgToTxt(String imagePath) throws IOException {
        File image = new File(imagePath);

        // Tesseract OCR 객체 생성
        ITesseract tesseract = new Tesseract();
        // tessdata 디렉토리 경로 설정 src/main/resources/tessdata
        String basePath =System.getProperty("user.dir") + "/tessdata";
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+basePath);
        Path path = Paths.get(basePath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        tesseract.setDatapath(basePath);
        // 한글 언어 설정
        tesseract.setLanguage("kor");
        try {
            // 이미지 파일을 BufferedImage로 읽어오기
            //BufferedImage processedImage = preprocessImage(image);
            BufferedImage bufferedImage = ImageIO.read(image);

            // OCR 수행
            //String result = tesseract.doOCR(processedImage);
            String result = tesseract.doOCR(bufferedImage);

            // OCR 결과 출력
            System.out.println("OCR Result:");
            System.out.println(result);


            //
            String searchText = "생 활 스 포 츠 지 도 사 자 격 증";









            if(result.contains(searchText)) {
                System.out.println("존재합니다");
                return "요건충족";
            }else{
                return "요건불충족";
            }
//            //텍스트 추출
//            String title;
//            String name;
//            String grade;
//            String category;




        } catch (IOException | TesseractException e) {
            e.printStackTrace();

        }
        return "요건불충족";
    }

    public List<String> convertPdfToImage(List<FileDTO> fileDTOList) throws IOException {
        List<String> imgpath = new ArrayList<>();
        //String outputDir = "src/main/resources/static/OCRImages/";
        String outputDir = System.getProperty("user.dir") + "/static/OCRImages/";
        File outputDirectory = new File(outputDir);
        //파일생성위치가 없으면 생성
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        for (FileDTO file : fileDTOList) {
            String filePath = file.getFilepath();
            System.out.println(filePath);

            File pdfFile = new File(filePath);

            try (PDDocument document = PDDocument.load(pdfFile)) {
                PDFRenderer pdfRenderer = new PDFRenderer(document);
                BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
                String outputFilePath = outputDir + file.getFileName() + ".png";
                ImageIO.write(image, "png", new File(outputFilePath));
                imgpath.add(outputFilePath);
            }

        }
        System.out.println(imgpath+"++++++++++++++++++++++++++++++++++");
        return imgpath;
    }

}
