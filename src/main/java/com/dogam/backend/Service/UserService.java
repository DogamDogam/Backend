package com.dogam.backend.Service;

import com.dogam.backend.Dto.UserInfoDto;
import com.dogam.backend.Model.UserInfo;
import com.dogam.backend.Repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    static ModelMapper modelMapper;
    private final LoginRepository loginRepository;

    public Optional<UserInfoDto> findByEmail(String email) {
        Optional<UserInfo> opt = Optional.empty();
        opt =loginRepository.findByuserEmail(email);
        UserInfo entity = opt.get();
        UserInfoDto dto = of(entity);
        return Optional.of(dto);
    }

    @Transactional
    public void saveUserInfo(UserInfoDto user) {
        loginRepository.save(user.toEntity());
    }

    // Entity -> DTO
    public static UserInfoDto of(UserInfo userInfo) {
        return modelMapper.map(userInfo, UserInfoDto.class);
    }
}
