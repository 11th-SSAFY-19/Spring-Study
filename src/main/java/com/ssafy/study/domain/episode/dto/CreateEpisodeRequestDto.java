package com.ssafy.study.domain.episode.dto;

import com.ssafy.study.domain.episode.entity.Episode;

public class CreateEpisodeRequestDto {

    private String title;
    private Long webtoonId;

    public String getTitle() {
        return title;
    }

    public Long getWebtoonId() {
        return webtoonId;
    }
}
