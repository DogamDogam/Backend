package com.dogam.backend.Repository;

import com.dogam.backend.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
