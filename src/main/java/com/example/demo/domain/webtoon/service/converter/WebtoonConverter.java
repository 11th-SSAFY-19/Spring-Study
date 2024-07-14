package com.example.demo.domain.webtoon.service.converter;

import com.example.demo.domain.webtoon.entity.Webtoon;
import com.example.demo.domain.webtoon.ui.dto.WebtoonRequest;
import com.example.demo.domain.webtoon.ui.dto.WebtoonResponse;

public class WebtoonConverter {

    public static Webtoon toWebtoon(WebtoonRequest.WebtoonDto webtoonDto) {
        return Webtoon.builder()
                .webtoonId(webtoonDto.getWebtoonId())
                .title(webtoonDto.getTitle())
                .publicationStatus(webtoonDto.getPublicationStatus())
                .noYouth(webtoonDto.isNoYouth())
                .build();
    }

    public static WebtoonResponse.WebtoonDto toWebtoonDto(Webtoon webtoon) {
        return WebtoonResponse.WebtoonDto
                .builder()
                .webtoonId(webtoon.getWebtoonId())
                .title(webtoon.getTitle())
                .publicationStatus(webtoon.getPublicationStatus())
                .noYouth(webtoon.isNoYouth())
                .build();
    }
}
