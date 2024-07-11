package com.ssafy.study.domain.webtoon.ui.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

public class WebtoonRequest {
    @Getter
    @RequiredArgsConstructor
    public static class WebtoonDto {
        private final String title;
        private final String publicationStatus;
        private final boolean noYouth;
    }
}
