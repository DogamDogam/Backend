package com.dogam.backend.Repository;

import com.dogam.backend.Dto.UserInfoDto;
import com.dogam.backend.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<UserInfo, Long> {
    // Select * FROM UserInfo WHERE user_email = "~@naver.com"
    // user_id, user_nickname, user_image
    Optional<UserInfo> findByuserEmail(String user_email);
}
