package com.dogam.backend.Controller;
import com.dogam.backend.Service.LoginService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
@CrossOrigin
public class LoginController {

    @GetMapping("/kakao")
    public String kakaoCallback(@RequestParam String code) {
        System.out.println("kakaoLogin");
        System.out.println(code);
        String access_token = LoginService.getKakaoAccessToken(code);
        LoginService.createUserInfo(access_token);
        return "로그인성공";
    }
}
