package com.example.demo.domain.webtoon.service;

import com.example.demo.domain.webtoon.entity.Webtoon;
import com.example.demo.domain.webtoon.repository.WebtoonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Service
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    @Autowired
    public WebtoonService(WebtoonRepository webtoonRepository) {
        this.webtoonRepository = webtoonRepository;
    }

    @PostMapping
    public Webtoon create(@RequestBody Webtoon webtoon) {
        return webtoonRepository.save(webtoon);
    }

    @GetMapping("/{webtoonId}")
    public Webtoon findByWebtoonId(@PathVariable(name = "webtoonId") Long webtoonId) {
        return webtoonRepository.findById(webtoonId).orElse(null);
    }

    @Transactional
    public Webtoon edit(@RequestBody Webtoon webtoon) {
        Webtoon oldWebtoon = webtoonRepository
                .findById(webtoon.getWebtoonId())
                .orElseThrow(() ->
                        new IllegalStateException("there is no webtoon of which webtoonId is " + webtoon.getWebtoonId()));
        // TODO: 이렇게 짜는거 맞음? 흠...
        Webtoon editedWebtoon = Webtoon.builder()
                .webtoonId(webtoon.getWebtoonId())
                .createdAt(oldWebtoon.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .publicationStatus(webtoon.getPublicationStatus() == null?
                        oldWebtoon.getPublicationStatus() :
                        webtoon.getPublicationStatus())
                .build();
        return webtoonRepository.save(editedWebtoon);
    }

    @DeleteMapping("/{webtoonId}")
    public void delete(@PathVariable(name = "webtoonId") Long webtoonId) {
        webtoonRepository.deleteById(webtoonId);
        return;
    }
}
