package com.dogam.backend.Model;

import com.dogam.backend.Dto.RequestPostDto;
import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="Post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String image;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String description;

    public Post(RequestPostDto requestPostDto) {
        this.image = requestPostDto.getImage();
        this.title = requestPostDto.getTitle();
        this.price = requestPostDto.getPrice();
        this.place = requestPostDto.getPlace();
        this.description = requestPostDto.getDescription();
    }
}
