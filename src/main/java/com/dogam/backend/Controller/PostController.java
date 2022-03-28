package com.dogam.backend.Controller;

import com.dogam.backend.Dto.RequestPostDto;
import com.dogam.backend.Model.Post;
import com.dogam.backend.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    //카테고리별 출력
    @GetMapping("/posts/category")
    public Optional<Post> fetchPosts(@RequestParam String category) {
        return postRepository.findByCategory(category);
    }

    //게시물별 출력
    @GetMapping("/post/{id}")
    public Optional<Post> findById(@PathVariable long id) { //model에 담음
        return postRepository.findById(id);
    }

}
