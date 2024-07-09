package com.example.project.login;

import com.example.project.OCR.OCRController;
import com.example.project.request.FileEntity;
import com.example.project.request.RequestEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.util.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class LoginControllerTest {
    @Autowired
    private LoginService service;
    @Test
    public void generateState() {
        SecureRandom random = new SecureRandom();
        System.out.println(new BigInteger(130, random).toString(32));
    }
    @Test
    public void test(){

        UserDTO admin = service.search("admin");
        System.out.println(admin);


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
    @Autowired
    UserService userService;

    @Test
    void tt(){
        userService.updatePoint("ffdr803",0);
        userService.updatePoint("trainer",0);
        userService.updatePoint("kjs0645",0);
        userService.updatePoint("ffdr802@kakao,com",0);
        userService.updatePoint("ppoza185",0);
        userService.updatePoint("Parkbs9701",0);
        userService.updatePoint("JS0829",0);
        userService.updatePoint("YJT11",0);
        userService.updatePoint("sdfgcdsvsd",0);
        userService.updatePoint("zxvxc",0);
        userService.updatePoint("ㅊ퓨",0);
        userService.updatePoint("dshdsfhgd",0);
        userService.updatePoint("dfhfhg",0);
        userService.updatePoint("dfsddfgdfg",0);
        userService.updatePoint("123hgdfgh",0);
        userService.updatePoint("1231",0);
        userService.updatePoint("2524523",0);
        userService.updatePoint("123fdsgdsfg",0);
        userService.updatePoint("dfgdfg",0);
        userService.updatePoint("123123",0);
        userService.updatePoint("sdfgsdg",0);
        userService.updatePoint("123dfxzvcv",0);
        userService.updatePoint("12376273",0);
        userService.updatePoint("123123gggg",0);
        userService.updatePoint("0-4581",0);



    }
    @Test
    public void sendSms() {

        String api_key = "NCSPDPGSX6ABHYGX";
        String api_secret = "RVUUTLKZSOSJ9D7FLZW9BR2OIOZZIMOJ";
        Message coolsms = new Message(api_key, api_secret);
        HashMap<String, String> params = new HashMap<String, String>();

        params.put("to", "01062813528");
        params.put("from", "01062813528");
        params.put("type", "SMS");
        params.put("text", "안녕하세요");
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }
    }

    @Test
    public void paytest() {
        // Set your secret key
        String secretKey = "DEV3EFB35F02021676106388E99F4AABBED5ECE0";

        // Create a RestTemplate instance
        RestTemplate restTemplate = new RestTemplate();

        // Create headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "SECRET_KEY " + secretKey);

        String partnerOrderId = UUID.randomUUID().toString();

        // Create request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("cid", "TC0ONETIME");
        requestBody.put("partner_order_id", partnerOrderId);
        requestBody.put("partner_user_id", "partner_user_id");
        requestBody.put("item_name", "초코파이");
        requestBody.put("quantity", "1");
        requestBody.put("total_amount", "2200");
        requestBody.put("vat_amount", "200");
        requestBody.put("tax_free_amount", "0");
        requestBody.put("approval_url", "http://127.0.0.1:8088/kakaopay/success");
        requestBody.put("fail_url", "http://127.0.0.1:8088/kakaopay/fail");
        requestBody.put("cancel_url", "http://127.0.0.1:8088/paymentCancel");

        // Create HttpEntity
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        // Send the request
        String url = "https://open-api.kakaopay.com/online/v1/payment/ready";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // Print the response
        System.out.println(response.getBody());
    }

}