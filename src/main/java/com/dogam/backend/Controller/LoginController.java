package com.dogam.backend.Controller;

import com.dogam.backend.Model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Controller
@CrossOrigin
public class LoginController {

    @GetMapping("/login")
    public String getLogin() {
        String url = "https://kauth.kakao.com/oauth/authorize?client_id=32563be2662a64d66f1e3547267b03df&redirect_uri=http://localhost:9090/kakao/callback&response_type=code";
        return "redirect:"+url;
    }

}
