package com.example.demo.domain.webtoon.dto;

import com.example.demo.domain.webtoon.entity.Webtoon;
import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class WebtoonResponse {

    private final String title;
    private final String publicationStatus;

    public WebtoonResponse(Webtoon webtoon) {
        this.title = webtoon.getTitle();
        this.publicationStatus = webtoon.getPublicationStatus();
    }
}
