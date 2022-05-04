package com.dogam.backend.Controller;

import com.dogam.backend.Dto.RequestPostDto;
import com.dogam.backend.Model.Post;
import com.dogam.backend.Repository.PostRepository;
import com.dogam.backend.Service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin
public class PostController {

    final private PostService postService;
    final private PostRepository postRepository;

    //게시물 전체 조회
    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> fetchPosts(@PageableDefault(size=5, sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(postService.selectPosts(pageable),HttpStatus.OK);
    }

    //게시물 저장
    @PostMapping("/posts")
    public ResponseEntity<String> postPost(@RequestBody RequestPostDto requestPostDto) {
        postService.savePost(new Post(requestPostDto));
        return new ResponseEntity<>("저장완료",HttpStatus.OK);
    }

    //카테고리별 출력
    @GetMapping("/posts/category/1")
    public ResponseEntity<Page<Post>> foodPosts(@PageableDefault(size=5, sort="id",direction = Sort.Direction.DESC) Pageable pageable)  {
        return new ResponseEntity<>(postService.findByCategory(1, pageable),HttpStatus.OK);

    }

    @GetMapping("/posts/category/2")
    public ResponseEntity<Page<Post>> deliveryPosts(@PageableDefault(size=5, sort="id",direction = Sort.Direction.DESC) Pageable pageable)  {
        return new ResponseEntity<>(postService.findByCategory(2, pageable),HttpStatus.OK);

    }

    @GetMapping("/posts/category/3")
    public ResponseEntity<Page<Post>> goodsPosts(@PageableDefault(size=5, sort="id",direction = Sort.Direction.DESC) Pageable pageable)  {
        return new ResponseEntity<>(postService.findByCategory(3, pageable),HttpStatus.OK);

    }

    //게시물 개별 출력
    @GetMapping("/post/{id}")
    public ResponseEntity<Post> findById(@PathVariable int id) { //model에 담음
        return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);

    }

    //게시물 수정
    @PutMapping("/post/{id}")
    public ResponseEntity<String> updateById(@PathVariable int id, @RequestBody Post post) {
        postService.updatePost(id, post);
        return new ResponseEntity<>("수정완료",HttpStatus.OK);
    }

    //게시물 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        postService.deletePost(id);
        return new ResponseEntity<>("삭제완료",HttpStatus.OK);
    }

    @GetMapping("posts/trade")
    public List<Post> selectTradingPosts() {
        return postRepository.selectTradingPosts();
    }

    @GetMapping("posts/wait")
    public List<Post> selectWaitingPosts() {
        return postRepository.selectWaitingPosts();
    }



}
