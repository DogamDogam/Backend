package com.dogam.backend.Controller;
import com.dogam.backend.Dto.UserInfoDto;
import com.dogam.backend.Model.UserInfo;
import com.dogam.backend.Service.LoginService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth")
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/kakao")
    public String kakaoCallback(@RequestParam String code) {
        System.out.println(code);
        String access_token = loginService.getKakaoAccessToken(code);
        Optional<List> opt_userinfodto = loginService.getUserInfo(access_token);
        List success_Info = opt_userinfodto.get();
        System.out.println("UserInfoDto" + success_Info);
        return "로그인 성공";
    }

}
