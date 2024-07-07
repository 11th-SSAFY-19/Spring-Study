package com.ssafy.study.domain.webtoon.controller;

import com.ssafy.study.domain.webtoon.dto.WebtoonDto;
import com.ssafy.study.domain.webtoon.entity.Webtoon;
import com.ssafy.study.domain.webtoon.service.WebtoonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webtoons")
public class WebtoonController {
    private final WebtoonService webtoonService;

    public WebtoonController(WebtoonService webtoonService) {
        this.webtoonService = webtoonService;
    }

    @PostMapping
    public ResponseEntity<Webtoon> createWebtoon(@RequestBody WebtoonDto webtoonDto) {
        Webtoon webtoon = webtoonService.createWebtoon(webtoonDto);
        return ResponseEntity.ok(webtoon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Webtoon> getWebtoonById(@PathVariable Long id) {
        Webtoon webtoon = webtoonService.getWebtoonById(id);
        return ResponseEntity.ok(webtoon);
    }
}
