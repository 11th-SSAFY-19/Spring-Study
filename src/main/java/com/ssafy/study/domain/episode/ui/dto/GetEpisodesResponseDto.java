package com.ssafy.study.domain.episode.ui.dto;

import com.ssafy.study.domain.episode.entity.Episode;

import java.util.List;
import java.util.stream.Collectors;

public class GetEpisodesResponseDto {

    List<GetEpisodeResponseDto> episodeList;

    public GetEpisodesResponseDto(List<GetEpisodeResponseDto> episodes) {
        this.episodeList = episodes;
    }

    public static GetEpisodesResponseDto from(List<Episode> episodes) {
        List<GetEpisodeResponseDto> episodeList = episodes.stream().map(GetEpisodeResponseDto::from).collect(Collectors.toList());
        for (GetEpisodeResponseDto episodeResult: episodeList) {
            System.out.println(episodeResult);
        }
        return new GetEpisodesResponseDto(episodeList);
    }

    public List<GetEpisodeResponseDto> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<GetEpisodeResponseDto> episodeList) {
        this.episodeList = episodeList;
    }
}
