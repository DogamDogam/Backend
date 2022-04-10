package com.dogam.backend.Controller;

import com.dogam.backend.Dto.RequestReplyDto;
import com.dogam.backend.Model.Post;
import com.dogam.backend.Model.Reply;
import com.dogam.backend.Repository.PostRepository;
import com.dogam.backend.Repository.ReplyRepository;
import com.dogam.backend.Service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/reply/{postId}")
    public Object fetchReplys(@PathVariable int postId) {
        return replyService.selectReplys(postId);
    }

    @PostMapping("/reply/{postId}")
    public String postReply(@PathVariable int postId, @RequestBody RequestReplyDto requestReplyDto) {
        return replyService.saveReply(postId, new Reply(requestReplyDto));
    }
}
