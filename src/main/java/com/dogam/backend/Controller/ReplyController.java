package com.dogam.backend.Controller;

import com.dogam.backend.Dto.RequestReplyDto;
import com.dogam.backend.Service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Optional<List>> fetchReplys(@PathVariable int postId) {
        return new ResponseEntity<>(replyService.selectReplys(postId), HttpStatus.OK);

    }

    @PostMapping("/reply/{postId}")
    public ResponseEntity<String> postReply(@PathVariable int postId, @RequestBody RequestReplyDto requestReplyDto) {
        replyService.saveReply(postId, requestReplyDto);
        return new ResponseEntity<>("댓글작성완료", HttpStatus.OK);
    }

    @DeleteMapping("/reply/{postId}/{id}")
    public ResponseEntity<String> deleteReply(@PathVariable int id) {
        replyService.deleteById(id);
        return new ResponseEntity<>("댓글삭제완료",HttpStatus.OK);
    }
}
