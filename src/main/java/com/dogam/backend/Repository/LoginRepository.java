package com.dogam.backend.Repository;

import com.dogam.backend.Model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<UserInfo, Long> {

    // SELECT * FROM UserInfo WHERE userEmail = userEmail
    Optional<List> findByuserEmail(String userEmail);
}
