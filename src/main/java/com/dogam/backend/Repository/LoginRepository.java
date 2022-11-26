package com.dogam.backend.Repository;

import com.dogam.backend.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<UserInfo, Long> {

    // SELECT userEmail FROM UserInfo
    UserInfo findByuserEmail(String userEmail);

}
