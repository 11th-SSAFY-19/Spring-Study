package com.ssafy.study.domain.webtoon.service;

import com.ssafy.study.domain.webtoon.entity.Webtoon;
import com.ssafy.study.domain.webtoon.dao.WebtoonRepository;
import com.ssafy.study.domain.webtoon.service.converter.WebtoonConverter;
import com.ssafy.study.domain.webtoon.ui.dto.WebtoonRequest;
import com.ssafy.study.domain.webtoon.ui.dto.WebtoonResponse;
import com.ssafy.study.global.common.exception.BaseException;
import com.ssafy.study.global.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WebtoonService {
    private final WebtoonRepository webtoonRepository;

    @Transactional(readOnly = false)
    public void create(WebtoonRequest.WebtoonDto webtoonDto) {
        Webtoon webtoon = WebtoonConverter.toWebtoon(webtoonDto);
        webtoonRepository.save(webtoon);
    }

    @Transactional(readOnly = true)
    public WebtoonResponse.WebtoonDto getWebtoonById(Long webtoonId) {
        return webtoonRepository.findById(webtoonId)
                .map(WebtoonConverter::toWebtoonDto)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.WEBTOON_NOT_FOUND));
    }

    @Transactional(readOnly = false)
    public void update(Long id, WebtoonRequest.WebtoonDto webtoonDto) {
        Webtoon webtoon = webtoonRepository.findById(id).orElseThrow(() ->
                        new IllegalStateException("there is no webtoon of which webtoonId is " + id));
        webtoon.updateAll(webtoonDto.getTitle(), webtoon.getPublicationStatus(), webtoon.isNoYouth());
    }

    @Transactional(readOnly = false)
    public void remove(Long webtoonId) {
        Webtoon webtoon = webtoonRepository.findById(webtoonId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.WEBTOON_NOT_FOUND));
        webtoonRepository.delete(webtoon);
    }
}
