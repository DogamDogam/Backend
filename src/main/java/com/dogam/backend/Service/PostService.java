package com.dogam.backend.Service;

import com.dogam.backend.Model.Post;
import com.dogam.backend.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public List<Post>  selectPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public void savePost(Post post) {
           postRepository.save(post);
    }

    public Optional<List> findByCategory(int id) {
        if (id == 1) return postRepository.findByCategory("식재료");
        else if( id ==2) return postRepository.findByCategory("배달비");
        else if (id == 3) return postRepository.findByCategory("물품");
        return Optional.empty();
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    @Transactional
    public void updatePost(int id, Post requestPost) {
        Post post = postRepository.findById(id)
                .orElseThrow(()-> {
                    return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다.");
                });
        post.setTitle(requestPost.getTitle());
        post.setDescription(requestPost.getDescription());
    }


    @Transactional
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

}
