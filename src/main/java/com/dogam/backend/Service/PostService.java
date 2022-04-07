package com.dogam.backend.Service;

import com.dogam.backend.Model.Post;
import com.dogam.backend.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    public List<Post>  selectPosts() {
        return postRepository.findAll();
    }

    public void savePost(Post post) {
           postRepository.save(post);
    }

    public Optional<List> findByCategory(int id) {
        if (id == 1) return postRepository.findByCategory("식재료");
        else if( id ==2) return postRepository.findByCategory("배달비");
        else if (id == 3) return postRepository.findByCategory("물품");
        return Optional.empty();
    }

    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }
}
