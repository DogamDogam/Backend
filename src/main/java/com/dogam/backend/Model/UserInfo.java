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
    private long userId;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String userNickname;

    @Column
    private String userImage;
}
