package com.ssafy.study.domain.webtoon.service;

import com.ssafy.study.domain.member.dto.MemberDto;
import com.ssafy.study.domain.member.entity.Member;
import com.ssafy.study.domain.webtoon.dto.WebtoonDto;
import com.ssafy.study.domain.webtoon.entity.Webtoon;
import com.ssafy.study.domain.webtoon.repository.WebtoonRepository;
import com.ssafy.study.global.common.BaseException;
import com.ssafy.study.global.common.BaseResponseStatus;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WebtoonService {
    private final WebtoonRepository webtoonRepository;

    public WebtoonService(WebtoonRepository webtoonRepository) {
        this.webtoonRepository = webtoonRepository;
    }

    public Webtoon createWebtoon(WebtoonDto webtoonDto) {
        Webtoon webtoon = new Webtoon(
                webtoonDto.getTitle(),
                webtoonDto.getPublicationStatus(),
                webtoonDto.isNoYouth()
        );
        return webtoonRepository.save(webtoon);
    }

    public Webtoon getWebtoonById(Long id) {
        Optional<Webtoon> webtoon = webtoonRepository.findById(id);
        if (webtoon.isPresent()) {
            return webtoon.get();
        } else {
//            throw new BaseException(BaseResponseStatus.WEBTOON_NOT_FOUND);
            throw new EntityNotFoundException("Webtoon not found with id: " + id);
        }
    }
}
