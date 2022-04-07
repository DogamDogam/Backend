package com.dogam.backend.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequiredArgsConstructor
@RequestMapping("/LoginBoard")
@CrossOrigin
public class LoginController {

    @GetMapping("/KaKaoLogin/callback")
    public String kakaoLogin(String code) {
        System.out.println("kakaoLogin");
        System.out.println(code);
        return "로그인성공";
    }
}
