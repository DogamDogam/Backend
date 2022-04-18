package com.dogam.backend.Dto;

import com.dogam.backend.Model.UserInfo;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {
    private int userId;
    private String userEmail;
    private String userNickname;
    private String userImage;

    // DTO -> Entity
    public UserInfo toEntity() {
        return UserInfo.builder()
                .userId(userId)
                .userEmail(userEmail)
                .userNickname(userNickname)
                .userImage(userImage)
                .build();
    }
}
