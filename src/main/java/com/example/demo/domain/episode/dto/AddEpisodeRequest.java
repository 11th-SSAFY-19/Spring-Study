package com.example.demo.domain.episode.dto;

import com.example.demo.domain.episode.entity.Episode;
import com.example.demo.domain.webtoon.entity.Webtoon;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class AddEpisodeRequest {

    private String title;
    private String content;
    private String postscript;
    private Long webtoonId;
    private Integer views;
    private boolean isPublic;
    private int neededCookieAmount;
    private LocalDateTime freeReleaseDate;

    public Episode toEntity(Webtoon webtoon) {
        return Episode.builder()
                .title(title)
                .content(content)
                .postscript(postscript)
                .views(views)
                .isPublic(isPublic)
                .neededCookieAmount(neededCookieAmount)
                .webtoon(webtoon)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .freeReleaseDate(LocalDateTime.now())
                .build();
    }

}
