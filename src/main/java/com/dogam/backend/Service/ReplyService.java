package com.dogam.backend.Service;

import com.dogam.backend.Dto.RequestReplyDto;
import com.dogam.backend.Model.Post;
import com.dogam.backend.Model.Reply;
import com.dogam.backend.Repository.PostRepository;
import com.dogam.backend.Repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    public List<Reply> selectReplys(int postId) {
            return replyRepository.findByPostId(postId);
    }

    public void saveReply(int postId, RequestReplyDto requestReplyDto) {
        Post post = postRepository.findById(postId).orElseThrow(() -> {
            return new IllegalArgumentException("댓글 쓰기 실패: 게시물 id를 찾을 수 없습니다.");
        }); //영속화
        Reply reply = Reply.builder()
                .post(post)
                .image(requestReplyDto.getImage())
                .content(requestReplyDto.getContent())
                .userId(requestReplyDto.getUserId())
                .userName(requestReplyDto.getUserName())
                .build();

        replyRepository.save(reply);
    }

    public void deleteById(int id) {
        replyRepository.deleteById(id);
    }
}
