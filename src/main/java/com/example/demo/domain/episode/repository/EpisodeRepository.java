package com.example.demo.domain.episode.repository;

import com.example.demo.domain.episode.entity.Episode;
import com.example.demo.domain.webtoon.entity.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findAllByWebtoon(Webtoon foundWebtoon);
}
