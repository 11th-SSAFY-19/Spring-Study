package com.ssafy.study.domain.episode.ui.dto;

public class CreateEpisodeRequestDto {

    private String title;
    private Long webtoonId;

    public String getTitle() {
        return title;
    }

    public Long getWebtoonId() {
        return webtoonId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setWebtoonId(long webtoonId) {
        this.webtoonId = webtoonId;
    }
}
