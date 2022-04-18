package com.dogam.backend.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, name="user_email")
    private String userEmail;

    @Column(nullable = false, name="user_nickname")
    private String userNickname;

    @Column(nullable = false, name="user_image")
    private String userImage;
}
