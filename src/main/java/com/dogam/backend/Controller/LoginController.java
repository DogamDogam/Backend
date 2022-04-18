package com.dogam.backend.Controller;
import com.dogam.backend.Dto.UserInfoDto;
import com.dogam.backend.Model.UserInfo;
import com.dogam.backend.Service.LoginService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth")
@CrossOrigin
public class LoginController {

    @GetMapping("/kakao")
    public String kakaoCallback(@RequestParam String code) {
        UserInfo userinfo = null;
        System.out.println("kakaoLogin");
        System.out.println(code);
        String access_token = LoginService.getKakaoAccessToken(code);
        Optional<UserInfoDto> success_Info = LoginService.getUserInfo(access_token);
        System.out.println("UserInfoDto" + success_Info);
        return "로그인 성공";
    }

}
