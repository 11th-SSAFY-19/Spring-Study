package com.ssafy.study.domain.episode.controller;

import com.ssafy.study.domain.episode.dto.CreateEpisodeRequestDto;
import com.ssafy.study.domain.episode.dto.GetEpisodeResponseDto;
import com.ssafy.study.domain.episode.dto.GetEpisodesResponseDto;
import com.ssafy.study.domain.episode.entity.Episode;
import com.ssafy.study.domain.episode.service.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {
    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @PostMapping
    public ResponseEntity<Episode> createEpisode(@RequestBody CreateEpisodeRequestDto createEpisodeRequestDto) {
        Episode episode = episodeService.createEpisode(createEpisodeRequestDto);
        return ResponseEntity.ok(episode);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEpisodeResponseDto> getEpisodeById(@PathVariable Long id) {
        GetEpisodeResponseDto episodeResult = episodeService.getEpisodeById(id);
        return ResponseEntity.ok(episodeResult);
    }

    @GetMapping("")
    public ResponseEntity<GetEpisodesResponseDto> getAllEpisodesByWebtoonId(@RequestParam Long webtoonId) {
        GetEpisodesResponseDto episodeListResult = episodeService.getAllEpisodesByWebtoonId(webtoonId);
        return ResponseEntity.ok(episodeListResult);
    }

}
