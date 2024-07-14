package com.example.demo.domain.webtoon.ui.dto;

import com.example.demo.domain.webtoon.entity.Webtoon;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

public class WebtoonResponse {

    @Getter
    public static class WebtoonDto {
        private final long webtoonId;
        private final String title;
        private final String publicationStatus;
        private final boolean noYouth;

        @Builder
        public WebtoonDto(long webtoonId, String title, String publicationStatus, boolean noYouth) {
            this.webtoonId = webtoonId;
            this.title = title;
            this.publicationStatus = publicationStatus;
            this.noYouth = noYouth;
        }
    }
}
