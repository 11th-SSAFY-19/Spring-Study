package com.ssafy.study.domain.webtoon.service.converter;

import com.ssafy.study.domain.webtoon.entity.Webtoon;
import com.ssafy.study.domain.webtoon.ui.dto.WebtoonRequest;
import com.ssafy.study.domain.webtoon.ui.dto.WebtoonResponse;

public class WebtoonConverter {
    public static Webtoon toWebtoon(WebtoonRequest.WebtoonDto webtoonDto) {
        return Webtoon.builder()
                .title(webtoonDto.getTitle())
                .noYouth(webtoonDto.isNoYouth())
                .publicationStatus(webtoonDto.getPublicationStatus())
                .build();
    }

    public static WebtoonResponse.WebtoonDto toWebtoonDto(Webtoon webtoon) {
        return WebtoonResponse.WebtoonDto
                .builder()
                .webtoonId(webtoon.getWebtoonId())
                .publicationStatus(webtoon.getPublicationStatus())
                .title(webtoon.getTitle())
                .noYouth(webtoon.isNoYouth())
                .build();
    }
}
