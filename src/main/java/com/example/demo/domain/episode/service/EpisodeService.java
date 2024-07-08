package com.example.demo.domain.episode.service;

import com.example.demo.domain.episode.dto.AddEpisodeRequest;
import com.example.demo.domain.episode.dto.UpdateEpisodeRequest;
import com.example.demo.domain.episode.entity.Episode;
import com.example.demo.domain.episode.repository.EpisodeRepository;
import com.example.demo.domain.webtoon.entity.Webtoon;
import com.example.demo.domain.webtoon.repository.WebtoonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;
    private final WebtoonRepository webtoonRepository;


    public Episode save(AddEpisodeRequest request) {
//        Webtoon webtoon = webtoonRepository.findById(request.getWebtoonId())
//                .orElseThrow(() -> new IllegalArgumentException("not found episodeId : " + request.getWebtoonId()));

        Webtoon webtoon = webtoonRepository.getReferenceById(request.getWebtoonId());

        return episodeRepository.save(request.toEntity(webtoon));
    }

    public Episode findById(long id) {
        return episodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found episodeId : " + id));
    }

    public void delete(long id) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found episodeId : " + id));

        episodeRepository.delete(episode);
    }

    @Transactional
    public Episode update(long id, UpdateEpisodeRequest request) {
        Episode episode = episodeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found episodeId : " + id));

        episode.update(request);
        return episode;
    }
}
