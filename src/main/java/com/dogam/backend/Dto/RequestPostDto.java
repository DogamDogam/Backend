package com.dogam.backend.Dto;

import lombok.Getter;

@Getter
public class RequestPostDto {
    private String image;
    private String title;
    private int price;
    private String place;
    private String description;
}
