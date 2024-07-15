package com.example.demo.domain.webtoon.ui.api;

import com.example.demo.domain.webtoon.ui.dto.WebtoonRequest;
import com.example.demo.domain.webtoon.ui.dto.WebtoonResponse;
import com.example.demo.domain.webtoon.entity.Webtoon;
import com.example.demo.domain.webtoon.service.WebtoonService;
import com.example.demo.global.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/webtoon")
public class WebtoonController {

    private final WebtoonService webtoonService;

    @PostMapping
    public BaseResponse<Void> createWebtoon(@RequestBody WebtoonRequest.WebtoonDto webtoonDto) {
        webtoonService.create(webtoonDto);
        return BaseResponse.successCreating();
    }

    @GetMapping("/{id}")
    public BaseResponse<WebtoonResponse.WebtoonDto> getWebtoonById(@PathVariable long id) {
        WebtoonResponse.WebtoonDto webtoonDto = webtoonService.getWebtoonById(id);
        return new BaseResponse<WebtoonResponse.WebtoonDto>(webtoonDto);
    }

    @PatchMapping("/{id}")
    public BaseResponse<Void> updateWebtoonById(@PathVariable long id, @RequestBody WebtoonRequest.WebtoonDto request) {
        webtoonService.updateWebtoonById(id, request);
        return BaseResponse.ok();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> deleteWebtoonById(@PathVariable long id) {
        webtoonService.deleteWebtoonById(id);
        return BaseResponse.ok();
    }

}
