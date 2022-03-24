package com.dogam.backend.Controller;

import com.dogam.backend.Dto.RequestPostDto;
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
       return postRepository.findAll();
    }

    @PostMapping("/posts")
    public String postPost(@RequestBody RequestPostDto requestPostDto) {
        Post post = new Post(requestPostDto);
        postRepository.save(post);
        return "저장완료";
    }
}
