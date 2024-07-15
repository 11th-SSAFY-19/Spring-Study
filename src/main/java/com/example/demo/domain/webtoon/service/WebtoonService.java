package com.example.demo.domain.webtoon.service;

import com.example.demo.domain.webtoon.service.converter.WebtoonConverter;
import com.example.demo.domain.webtoon.ui.dto.WebtoonRequest;
import com.example.demo.domain.webtoon.entity.Webtoon;
import com.example.demo.domain.webtoon.repository.WebtoonRepository;
import com.example.demo.domain.webtoon.ui.dto.WebtoonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class WebtoonService {

    private final WebtoonRepository webtoonRepository;

    @Transactional(readOnly = false)
    public void create(WebtoonRequest.WebtoonDto webtoonDto) {
        Webtoon webtoon = WebtoonConverter.toWebtoon(webtoonDto);
        webtoonRepository.save(webtoon);
    }

    @Transactional(readOnly = true)
    public WebtoonResponse.WebtoonDto getWebtoonById(long id) {
        Webtoon webtoon = webtoonRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("not found webtoonId : " + id));
        return WebtoonConverter.toWebtoonDto(webtoon);
    }

    @Transactional(readOnly = false)
    public void updateWebtoonById(long id, WebtoonRequest.WebtoonDto request) {
        Webtoon webtoon = webtoonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found webtoonId : " + id));

        webtoon.updateAll(request.getTitle(), request.getPublicationStatus(), request.isNoYouth());
    }

    @Transactional(readOnly = false)
    public void deleteWebtoonById(long id) {
        Webtoon webtoon = webtoonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found webtoonId : " + id));
        webtoonRepository.delete(webtoon);
    }


}
