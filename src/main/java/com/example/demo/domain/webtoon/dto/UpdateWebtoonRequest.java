package com.example.demo.domain.webtoon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateWebtoonRequest {
    private String title;
    private String publicationStatus;
    private boolean noYouth;
}
