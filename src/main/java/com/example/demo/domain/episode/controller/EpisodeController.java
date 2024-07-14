package com.example.demo.domain.episode.controller;

import com.example.demo.domain.episode.dto.AddEpisodeRequest;
import com.example.demo.domain.episode.dto.EpisodeResponse;
import com.example.demo.domain.episode.dto.UpdateEpisodeRequest;
import com.example.demo.domain.episode.entity.Episode;
import com.example.demo.domain.episode.service.EpisodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/episode")
public class EpisodeController {

    private final EpisodeService episodeService;

    @PostMapping
    public ResponseEntity<Episode> addEpisode(@RequestBody AddEpisodeRequest request) {
        Episode savedEpisode = episodeService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedEpisode);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EpisodeResponse> findEpisode(@PathVariable long id) {
        Episode episode = episodeService.findById(id);

        return ResponseEntity.ok()
                .body(new EpisodeResponse(episode));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpisode(@PathVariable long id) {
        episodeService.delete(id);

        return ResponseEntity.ok()
                .build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Episode> updateEpisode(@PathVariable long id, @RequestBody UpdateEpisodeRequest request) {
        Episode updatedEpisode = episodeService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedEpisode);
    }

}
