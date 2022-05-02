package com.dogam.backend.Repository;

import com.dogam.backend.Model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    //SELECT * FROM user WHERE category = "식재료";
    Page<Post> findByCategory(String Category, Pageable pageable);

    // 일반 SQL쿼리
    // select * from Post a, (select * from Reply group by post_id) b ON a.id = b.post_id
    @Query(value = "select * from Post a INNER JOIN (select * from Reply group by post_id) b ON a.id = b.post_id", nativeQuery = true)
    public List<Post> selectTradingPosts();

    //중복 문제
    @Query(value = "select * from Post a LEFT JOIN Reply b ON a.id = b.post_id where b.id is null", nativeQuery = true)
    public List<Post> selectWaitingPosts();

}
