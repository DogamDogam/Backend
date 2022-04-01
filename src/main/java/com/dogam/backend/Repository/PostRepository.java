package com.dogam.backend.Repository;

import com.dogam.backend.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    //SELECT * FROM user WHERE category = "식재료";
    Optional<List> findByCategory(String Category);
}
