package com.dogam.backend.Controller;
import com.dogam.backend.Dto.UserInfoDto;
import com.dogam.backend.Model.UserInfo;
import com.dogam.backend.Service.LoginService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/oauth")
@CrossOrigin
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) {
        String access_token = loginService.getKakaoAccessToken(code);
        loginService.getUserInfo(access_token);
    }

    @GetMapping("/kakao")
    public ResponseEntity<List<UserInfoDto>> getUserInfo() {
        return new ResponseEntity<>(loginService.getUserInfoDto(), HttpStatus.OK);
    }
}
