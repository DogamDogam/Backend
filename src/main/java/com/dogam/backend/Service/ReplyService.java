package com.dogam.backend.Service;

import com.dogam.backend.Dto.RequestReplyDto;
import com.dogam.backend.Model.Post;
import com.dogam.backend.Model.Reply;
import com.dogam.backend.Repository.PostRepository;
import com.dogam.backend.Repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public Object selectReplys(int postId) {
        if (postRepository.findById(postId).isEmpty()){
            return "해당 게시물이 없습니다.";
        }
        else {
            return replyRepository.findByPostId(postId);
        }
    }

    public String saveReply(int postId, Reply reply) {
        Post post = postRepository.findById(postId).orElseThrow(() -> {
            return new IllegalArgumentException("댓글 쓰기 실패: 게시물 id를 찾을 수 없습니다.");
        }); //영속화

        replyRepository.save(reply);
        return "댓글쓰기 완료";
    }
}
