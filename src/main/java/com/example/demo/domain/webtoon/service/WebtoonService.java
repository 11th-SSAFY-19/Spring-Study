package com.example.demo.domain.webtoon.service;

import com.example.demo.domain.webtoon.dto.AddWebtoonRequest;
import com.example.demo.domain.webtoon.dto.UpdateWebtoonRequest;
import com.example.demo.domain.webtoon.entity.Webtoon;
import com.example.demo.domain.webtoon.repository.WebtoonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    public Webtoon save(AddWebtoonRequest request) {
        return webtoonRepository.save(request.toEntity());
    }

    public Webtoon findById(long id) {
        return webtoonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found webtoonId : " + id));
    }

    public void delete(long id) {
        Webtoon webtoon = webtoonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found webtoonId : " + id));

        webtoonRepository.delete(webtoon);
    }

    @Transactional // ??
    public Webtoon update(long id, UpdateWebtoonRequest request) {
        Webtoon webtoon = webtoonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found webtoonId : " + id));

        webtoon.update(request.getTitle(), request.getPublicationStatus(), request.isNoYouth());

        return webtoon;
    }
}
