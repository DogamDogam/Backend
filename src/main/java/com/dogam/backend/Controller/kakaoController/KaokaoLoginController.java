package com.dogam.backend.Controller.kakaoController;

import com.dogam.backend.Model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
public class KaokaoLoginController {

    public OAuthToken oauthToken;

    @GetMapping("/kakao/callback")
    public String kakaoCallback(String code) {
        //인가 코드 받기
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        StringBuilder sb = new StringBuilder();
        sb.append("grant_type=authorization_code&client_id=32563be2662a64d66f1e3547267b03df&redirect_uri=http://localhost:9090/kakao/callback&code="+code);
        String body = sb.toString();

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, requestEntity, String.class); //토큰 받기
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        ObjectMapper objectMapper = new ObjectMapper();
//        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response, OAuthToken.class);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("카카오 액세스 토큰: "+oauthToken.getAccess_token());
        oauthToken.setAccess_token(oauthToken.getAccess_token());

        return "로그인";

    }

    @GetMapping("/logout")
    public String logout() {

        System.out.println(oauthToken.getAccess_token());

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+oauthToken.getAccess_token());
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://kapi.kakao.com/v1/user/logout", HttpMethod.POST, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();
        System.out.println("Response status: " + status);
        System.out.println(response);

        return "로그아웃";
    }
}
