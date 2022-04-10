package com.dogam.backend.Repository;

import com.dogam.backend.Model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
    //SELECT * FROM user WHERE postId = postId;
    Optional<List> findByPostId(int postId);
}
