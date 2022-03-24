package com.dogam.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPostDto {
    private String image;
    private String title;
    private int price;
    private String place;
    private String category;
    private int numOfpeople;
    private String description;
}
