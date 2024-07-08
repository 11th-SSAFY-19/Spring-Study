package com.example.demo.domain.webtoon.controller;

import com.example.demo.domain.webtoon.entity.Webtoon;
import com.example.demo.domain.webtoon.service.WebtoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webtoons")
public class WebtoonController {

    private final WebtoonService webtoonService;

    @Autowired
    public WebtoonController(WebtoonService webtoonService) {
        this.webtoonService = webtoonService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Webtoon webtoon) {
        // TODO: parameter를 CreateWebtoonDto로 수정
        webtoonService.create(webtoon);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/{webtoonId}")
    public ResponseEntity<Webtoon> findByWebtoonId(@PathVariable(name = "webtoonId") Long webtoonId) {
        Webtoon webtoon = webtoonService.findByWebtoonId(webtoonId);
        return new ResponseEntity<>(webtoon, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Webtoon> edit(@RequestBody Webtoon webtoon) {
        Webtoon editedWebtoon = webtoonService.edit(webtoon);
        return new ResponseEntity<>(editedWebtoon, HttpStatus.OK);
    }

    @DeleteMapping("/{webtoonId}")
    public ResponseEntity<String> delete(@PathVariable(name = "webtoonId") Long webtoonId) {
        webtoonService.delete(webtoonId);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
}
