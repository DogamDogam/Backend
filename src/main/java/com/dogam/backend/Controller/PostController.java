package com.dogam.backend.Controller;

import com.dogam.backend.Dto.RequestPostDto;
import com.dogam.backend.Model.Post;
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
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin
public class PostController {

    final private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Page<Post>> fetchPosts(@PageableDefault(size=5, sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
        return new ResponseEntity<>(postService.selectPosts(pageable),HttpStatus.OK);
        //       return new ResponseEntity<>(postService.selectPosts(pageable), HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<String> postPost(@RequestBody RequestPostDto requestPostDto) {
        postService.savePost(new Post(requestPostDto));
        return new ResponseEntity<>("저장완료",HttpStatus.OK);
    }

    //카테고리별 출력
    @GetMapping("/posts/category/1")
    public ResponseEntity<Optional<List>> foodPosts()  {
        return new ResponseEntity<>(postService.findByCategory(1),HttpStatus.OK);

    }

    @GetMapping("/posts/category/2")
    public ResponseEntity<Optional<List>> deliveryPosts()  {
        return new ResponseEntity<>(postService.findByCategory(2),HttpStatus.OK);

    }

    @GetMapping("/posts/category/3")
    public ResponseEntity<Optional<List>> goodsPosts()  {
        return new ResponseEntity<>(postService.findByCategory(3),HttpStatus.OK);

    }

    //게시물별 출력
    @GetMapping("/post/{id}")
    public ResponseEntity<Optional<Post>> findById(@PathVariable int id) { //model에 담음
        return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);

    }

    @PutMapping("/post/{id}")
    public ResponseEntity<String> updateById(@PathVariable int id, @RequestBody Post post) {
        postService.updatePost(id, post);
        return new ResponseEntity<>("수정완료",HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        postService.deletePost(id);
        return new ResponseEntity<>("삭제완료",HttpStatus.OK);
    }
}
