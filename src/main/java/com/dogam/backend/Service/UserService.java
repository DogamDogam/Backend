package com.dogam.backend.Service;

import com.dogam.backend.Dto.UserInfoDto;
import com.dogam.backend.Model.UserInfo;
import com.dogam.backend.Repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    private final ModelMapper modelMapper;

    private final LoginRepository loginRepository;

    public List<UserInfoDto> findAll() {
        List<UserInfoDto> dto = toDtoClass(loginRepository.findAll());
        return dto;
    }

    public UserInfoDto findByEmail(String email) {
        Optional<UserInfo> opt_userInfo = Optional.ofNullable(loginRepository.findByuserEmail(email));
        UserInfoDto userInfoDto = new UserInfoDto();
        if (opt_userInfo.isPresent()) {
            UserInfo userInfo = opt_userInfo.get();
            userInfoDto = of(userInfo);
        }
        return userInfoDto;
    }

    @Transactional
    public void saveUserInfo(UserInfo user) {
        loginRepository.save(user);
        System.out.println("회원정보 저장");
    }

    // Entity -> DTO
    public UserInfoDto of(UserInfo userInfo) {
        return modelMapper.map(userInfo, UserInfoDto.class);
    }

    // Entity Class -> DTO Class
    public List<UserInfoDto> toDtoClass(List<UserInfo> entityList) {
        List<UserInfoDto> DtoList = new ArrayList<>();
        for(UserInfo entity : entityList) {
            DtoList.add(of(entity));
        }
        return DtoList;
        // https://dbbymoon.tistory.com/4
    }
}
