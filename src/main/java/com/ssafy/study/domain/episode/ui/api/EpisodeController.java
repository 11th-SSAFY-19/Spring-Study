package com.ssafy.study.domain.episode.ui.api;

import com.ssafy.study.domain.episode.ui.dto.CreateEpisodeRequestDto;
import com.ssafy.study.domain.episode.ui.dto.GetEpisodeResponseDto;
import com.ssafy.study.domain.episode.ui.dto.GetEpisodesResponseDto;
import com.ssafy.study.domain.episode.entity.Episode;
import com.ssafy.study.domain.episode.service.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
