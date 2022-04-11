package com.dogam.backend.Controller;

import com.dogam.backend.Dto.RequestReplyDto;
import com.dogam.backend.Service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping("/reply/{postId}")
    public Optional<List> fetchReplys(@PathVariable int postId) {
        return replyService.selectReplys(postId);
    }

    @PostMapping("/reply/{postId}")
    public String postReply(@PathVariable int postId, @RequestBody RequestReplyDto requestReplyDto) {
        replyService.saveReply(postId, requestReplyDto);
        return "댓글작성완료";
    }

    @DeleteMapping("/reply/{postId}/{id}")
    public String deleteReply(@PathVariable int id) {
        replyService.deleteById(id);
        return "댓글삭제완료";
    }
}
