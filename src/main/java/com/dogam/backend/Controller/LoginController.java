package com.dogam.backend.Controller;
import com.dogam.backend.Dto.UserInfoDto;
import com.dogam.backend.Model.UserInfo;
import com.dogam.backend.Repository.LoginRepository;
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
    private final LoginRepository loginRepository;
    private String userEmail;

    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) {
        userEmail = null;
        System.out.println(code);
        String access_token = loginService.getKakaoAccessToken(code);
        userEmail = loginService.getUserInfo(access_token);
        System.out.println("kakao : " + userEmail);
    }

    @GetMapping("/getUser")
    public ResponseEntity<UserInfoDto> getUserInfo()
    {
        System.out.println("getuser : " + userEmail);
        return new ResponseEntity(loginService.login(userEmail), HttpStatus.OK);
    }
}
