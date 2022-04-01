package com.dogam.backend.Controller;

import com.dogam.backend.Dto.RequestPostDto;
import com.dogam.backend.Model.Post;
import com.dogam.backend.Repository.PostRepository;
import com.dogam.backend.Service.PostService;
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
    final private PostService postService;

    @GetMapping("/posts")
    public List<Post> fetchPosts() {
       return postService.selectPosts();
    }

    @PostMapping("/posts")
    public String postPost(@RequestBody RequestPostDto requestPostDto) {
        postService.savePost(new Post(requestPostDto));
        return "저장완료";
    }

    //카테고리별 출력
    @GetMapping("/posts/category/1")
    public Optional<List> foodPosts()  {
        return postService.findByCategory(1);
    }

    @GetMapping("/posts/category/2")
    public Optional<List> deliveryPosts()  {
        return postService.findByCategory(2);
    }

    @GetMapping("/posts/category/3")
    public Optional<List> goodsPosts()  {
        return postService.findByCategory(3);
    }

    //게시물별 출력
    @GetMapping("/post/{id}")
    public Optional<Post> findById(@PathVariable long id) { //model에 담음
        return postService.findById(id);
    }

}
