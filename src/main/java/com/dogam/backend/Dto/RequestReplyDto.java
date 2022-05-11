package com.dogam.backend.Dto;

import com.dogam.backend.Model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestReplyDto {
    private String image;
    private String content;
    private int postId;
    private long userId;
    private String userName;
}
