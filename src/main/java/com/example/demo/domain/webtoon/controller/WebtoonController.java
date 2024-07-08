package com.example.demo.domain.webtoon.controller;

import com.example.demo.domain.webtoon.dto.AddWebtoonRequest;
import com.example.demo.domain.webtoon.dto.UpdateWebtoonRequest;
import com.example.demo.domain.webtoon.dto.WebtoonResponse;
import com.example.demo.domain.webtoon.entity.Webtoon;
import com.example.demo.domain.webtoon.service.WebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class WebtoonController {

    private final WebtoonService webtoonService;

    @PostMapping("/api/webtoon")
    public ResponseEntity<Webtoon> addWebtoon(@RequestBody AddWebtoonRequest request) {
        Webtoon savedWebtoon = webtoonService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedWebtoon);
    }

    @GetMapping("/api/webtoon/{id}")
    public ResponseEntity<WebtoonResponse> findWebtoon(@PathVariable long id) {
        Webtoon webtoon = webtoonService.findById(id);

        return ResponseEntity.ok()
                .body(new WebtoonResponse(webtoon));
    }

    @DeleteMapping("/api/webtoon/{id}")
    public ResponseEntity<Void> deleteWebtoon(@PathVariable long id) {
        webtoonService.delete(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/webtoon/{id}")
    public ResponseEntity<Webtoon> updateWebtoon(@PathVariable long id, @RequestBody UpdateWebtoonRequest request) {
        Webtoon updatedWebtoon = webtoonService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedWebtoon);
    }

}
