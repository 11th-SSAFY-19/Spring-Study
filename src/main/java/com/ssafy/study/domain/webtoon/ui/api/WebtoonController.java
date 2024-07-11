package com.ssafy.study.domain.webtoon.ui.api;

import com.ssafy.study.domain.webtoon.service.WebtoonService;
import com.ssafy.study.domain.webtoon.ui.dto.WebtoonRequest;
import com.ssafy.study.domain.webtoon.ui.dto.WebtoonResponse;
import com.ssafy.study.global.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webtoons")
@RequiredArgsConstructor
public class WebtoonController {
    private final WebtoonService webtoonService;

    @PostMapping
    public BaseResponse<Void> create(@RequestBody WebtoonRequest.WebtoonDto webtoonDto) {
        webtoonService.create(webtoonDto);
        return BaseResponse.successCreating();
    }

    @GetMapping("/{id}")
    public BaseResponse<WebtoonResponse.WebtoonDto> getWebtoon(@PathVariable Long id) {
        WebtoonResponse.WebtoonDto webtoonDto = webtoonService.getWebtoonById(id);
        return new BaseResponse<WebtoonResponse.WebtoonDto>(webtoonDto);
    }

    @PatchMapping("/{id}")
    public BaseResponse<Void> update(@PathVariable Long id, @RequestBody WebtoonRequest.WebtoonDto webtoonDto) {
        webtoonService.update(id, webtoonDto);
        return BaseResponse.ok();
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Void> remove(@PathVariable(name = "id") Long webtoonId) {
        webtoonService.remove(webtoonId);
        return BaseResponse.ok();
    }
}
