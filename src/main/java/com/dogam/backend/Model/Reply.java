package com.dogam.backend.Model;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private String image;

    @Column(nullable = false)
    private String content;

    @ManyToOne //여러개의 답변은 하나의 게시물에 존재
    @JoinColumn(name="postId")
    private Post post;

    @CreationTimestamp
    private Timestamp createDate;
}

