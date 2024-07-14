package com.ssafy.study.domain.episode.service;

import com.ssafy.study.domain.episode.ui.dto.CreateEpisodeRequestDto;
import com.ssafy.study.domain.episode.ui.dto.GetEpisodeResponseDto;
import com.ssafy.study.domain.episode.ui.dto.GetEpisodesResponseDto;
import com.ssafy.study.domain.episode.entity.Episode;
import com.ssafy.study.domain.episode.dao.EpisodeRepository;
import com.ssafy.study.domain.webtoon.entity.Webtoon;
import com.ssafy.study.domain.webtoon.dao.WebtoonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EpisodeService {
    private static final Logger log = LoggerFactory.getLogger(EpisodeService.class);
    private final EpisodeRepository episodeRepository;
    private final WebtoonRepository webtoonRepository;

    public EpisodeService(EpisodeRepository episodeRepository, WebtoonRepository webtoonRepository) {
        this.episodeRepository = episodeRepository;
        this.webtoonRepository = webtoonRepository;
    }

    public Episode createEpisode(CreateEpisodeRequestDto createEpisodeRequestDto) {
        Webtoon foundWebtoon = webtoonRepository.findById(createEpisodeRequestDto.getWebtoonId())
                .orElseThrow(() -> new EntityNotFoundException("Webtoon not found with id: " + createEpisodeRequestDto.getWebtoonId()));

        Episode episode = Episode.builder()
                .title(createEpisodeRequestDto.getTitle())
                .webtoon(foundWebtoon)
                .build();

        return episodeRepository.save(episode);
    }

    public GetEpisodeResponseDto getEpisodeById(Long id) {
        return episodeRepository.findById(id)
                .map(GetEpisodeResponseDto::from)
                .orElseThrow(()-> new EntityNotFoundException("Episode not found with id: " + id));
    }

    public GetEpisodesResponseDto getAllEpisodesByWebtoonId(Long webtoonId) {
        Webtoon foundWebtoon = webtoonRepository.findById(webtoonId)
                .orElseThrow(() -> new EntityNotFoundException("Webtoon not found with id: " + webtoonId));

        List<Episode> episodeList = episodeRepository.findAllByWebtoon(foundWebtoon);
        return GetEpisodesResponseDto.from(episodeList);
    }
}
