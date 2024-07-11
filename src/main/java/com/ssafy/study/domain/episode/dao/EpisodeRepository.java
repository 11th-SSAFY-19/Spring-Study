package com.ssafy.study.domain.episode.dao;

import com.ssafy.study.domain.episode.entity.Episode;
import com.ssafy.study.domain.webtoon.entity.Webtoon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findAllByWebtoon(Webtoon foundWebtoon);
}
