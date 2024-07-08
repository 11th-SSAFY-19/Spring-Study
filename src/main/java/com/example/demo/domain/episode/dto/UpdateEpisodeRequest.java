package com.example.demo.domain.episode.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UpdateEpisodeRequest {
    private final String title;
    private final String content;
}
