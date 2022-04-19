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

    public Optional<List> findByEmail(String email) {
        Optional<List> opt = loginRepository.findByuserEmail(email);
        if (!opt.isPresent()) {
            throw new IllegalArgumentException();
        }
        return opt;
    }

    @Transactional
    public void saveUserInfo(UserInfoDto user) {
        loginRepository.save(user.toEntity());
    }

    // Entity -> DTO
    public UserInfoDto of(UserInfo userInfo) {
        return modelMapper.map(userInfo, UserInfoDto.class);
    }
}
