package com.dogam.backend.Model;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @Column(nullable = false)
    private long userId;

    @ManyToOne //여러개의 답변은 하나의 게시물에 존재
    @JoinColumn(name="postId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;

    @CreationTimestamp
    private Timestamp createDate;
}

