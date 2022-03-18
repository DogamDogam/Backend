package com.dogam.backend.Controller;

import com.dogam.backend.Model.Post;
import com.dogam.backend.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                .title("1 게시물")
                .description("1 내용")
                .price(20000)
                .place("부산")
                .build();

        Post post2 = Post.builder()
                .image("v.png")
                .title("2 게시물")
                .description("2 내용")
                .price(30000)
                .place("광진구")
                .build();

        postRepository.save(post1);
        postRepository.save(post2);
        return postRepository.findAll();
    }

    @PostMapping("/posts")
    public void postPost(@RequestBody Post p) {
        System.out.println(p.getImage());
        System.out.println(p.getTitle());
        System.out.println(p.getPrice());
        System.out.println(p.getPlace());
        System.out.println(p.getDescription());

    }
}
