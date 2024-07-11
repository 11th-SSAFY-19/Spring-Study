package com.ssafy.study.domain.webtoon.ui.dto;

import jakarta.persistence.Column;
import lombok.Builder;

public class WebtoonResponse {
    @Builder
    public record WebtoonDto(Long webtoonId, String title, String publicationStatus, boolean noYouth) {
    }
}
