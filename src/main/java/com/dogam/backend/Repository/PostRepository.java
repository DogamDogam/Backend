package com.dogam.backend.Repository;

import com.dogam.backend.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    //SELECT * FROM user WHERE category = "식재료";
    Optional<Post> findByCategory(String Category);
}
