package com.dogam.backend.Model;

import lombok.*;
import com.dogam.backend.Dto.RequestPostDto;
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
    private int id;

    @Column(nullable = true)
    private String image;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String place;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer numOfpeople;

    @Column(nullable = false)
    private String description;

    public Post(RequestPostDto requestPostDto) {
        this.image = requestPostDto.getImage();
        this.title = requestPostDto.getTitle();
        this.price = requestPostDto.getPrice();
        this.place = requestPostDto.getPlace();
        this.numOfpeople = requestPostDto.getNumOfpeople();
        this.category = requestPostDto.getCategory();
        this.description = requestPostDto.getDescription();
    }
}
