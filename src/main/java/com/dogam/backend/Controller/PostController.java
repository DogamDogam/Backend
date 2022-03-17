package com.dogam.backend.Controller;

import com.dogam.backend.Model.Post;
import com.dogam.backend.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin
public class PostController {

    final private PostRepository postRepository;

    @GetMapping("/posts")
    public List<Post> fetchPosts() {
        Post post1 = Post.builder()
                .image("c.png")
                .title("2 게시물")
                .content("2 내용")
                .price(20000)
                .place("부산")
                .build();

        Post post2 = Post.builder()
                .image("c.png")
                .title("2 게시물")
                .content("2 내용")
                .price(20000)
                .place("부산")
                .build();

        Post post3 = Post.builder()
                .image("v.png")
                .title("3 게시물")
                .content("3 내용")
                .price(30000)
                .place("광진구")
                .build();

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
        return postRepository.findAll();
    }
}
