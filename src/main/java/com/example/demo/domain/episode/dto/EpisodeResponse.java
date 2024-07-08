package com.example.demo.domain.episode.dto;

import com.example.demo.domain.episode.entity.Episode;
import lombok.Getter;

@Getter
public class EpisodeResponse {

    private final String title;
    private final String content;

    public EpisodeResponse(Episode episode) {
        this.title = episode.getTitle();
        this.content = episode.getContent();
    }
}
