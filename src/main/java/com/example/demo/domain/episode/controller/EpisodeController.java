package com.example.demo.domain.episode.controller;

import com.example.demo.domain.episode.entity.Episode;
import com.example.demo.domain.episode.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/episodes/")
public class EpisodeController {

    private final EpisodeService episodeService;

    @Autowired
    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @PostMapping
    public ResponseEntity<String> create(Episode episode) {
        episodeService.create(episode);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/{episodeId}")
    public ResponseEntity<Episode> findByEpisodeId(@PathVariable(name = "episodeId") Long episodeId) {
        Episode episode = episodeService.findByEpisodeId(episodeId);
        return new ResponseEntity<>(episode, HttpStatus.OK);
    }
}
