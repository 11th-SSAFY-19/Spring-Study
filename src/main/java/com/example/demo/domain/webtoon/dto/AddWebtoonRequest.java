package com.example.demo.domain.webtoon.dto;

import com.example.demo.domain.webtoon.entity.Webtoon;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddWebtoonRequest {

    private String title;
    private String publicationStatus;
    private boolean noYouth;

    public Webtoon toEntity() {
        return Webtoon.builder()
                .title(title)
                .publicationStatus(publicationStatus)
                .noYouth(noYouth)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
