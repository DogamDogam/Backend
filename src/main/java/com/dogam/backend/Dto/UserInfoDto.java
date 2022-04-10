package com.dogam.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private int user_id;
    private String user_email;
    private String user_nickname;
    private String user_image;
}
