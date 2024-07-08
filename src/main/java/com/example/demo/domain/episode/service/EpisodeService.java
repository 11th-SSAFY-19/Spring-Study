package com.example.demo.domain.episode.service;

import com.example.demo.domain.episode.entity.Episode;
import com.example.demo.domain.episode.repository.EpisodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EpisodeService {
    private final EpisodeRepository episodeRepository;

    @Autowired
    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    public Episode findByEpisodeId(Long episodeId) {
        return episodeRepository.findById(episodeId).orElse(null);
    }

    public Episode create(Episode episode) {
        return episodeRepository.save(episode);
    }

    public void delete(Long episodeId) {
        episodeRepository.deleteById(episodeId);
        return;
    }

    @Transactional
    public Episode edit(Episode episode) {
        Episode oldEpisode = episodeRepository
                .findById(episode.getEpisodeId())
                .orElseThrow(() ->
                        new IllegalStateException("there is no episode of which episodeId is " + episode.getEpisodeId()));
        Episode editedEpisode = Episode.builder()
                .episodeId(episode.getEpisodeId())
                .updatedAt(LocalDateTime.now())
                .title(episode.getTitle() == null?
                        oldEpisode.getTitle() :
                        episode.getTitle())
                .createdAt(episode.getCreatedAt())
                .build();
        return episodeRepository.save(editedEpisode);
    }
}
