package com.ssafy.study.domain.episode.ui.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.study.domain.episode.entity.Episode;
import com.ssafy.study.domain.episode.ui.dto.CreateEpisodeRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EpisodeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CreateEpisodeRequestDto testEpisodeRequestDto;

    @BeforeEach
    public void setup() {
        testEpisodeRequestDto = new CreateEpisodeRequestDto();
        testEpisodeRequestDto.setTitle("testTitle");
        testEpisodeRequestDto.setWebtoonId(1L);
    }

    @Test
    void 같은_사용자가_같은_에피소드_생성_요청을_따닥할때_하나만_성공한다() throws Exception {
        // [given]
        String episodeInfoJson = objectMapper.writeValueAsString(testEpisodeRequestDto);

        // [when]
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/episodes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding(StandardCharsets.UTF_8.displayName())
                    .content(episodeInfoJson);

        // [then]
        // 첫 번째 요청은 성공해야 한다.
        executorService.submit(() -> {
            try {
                latch.await();
                mockMvc.perform(requestBuilder)
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // 두 번째 요청은 실패해야 한다.
        executorService.submit(() -> {
            try {
                latch.await();
                mockMvc.perform(requestBuilder)
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isTooManyRequests());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        latch.countDown();  // 두 쓰레드가 동시에 실행되도록 함

        executorService.shutdown();
        while (!executorService.isTerminated()){}   // 모든 작업이 완료될 때까지 기다림
    }

    @Test
    void getEpisodeById() {
        // [given]

        // [when]
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/episodes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName());

        // [then]
        // 첫 번째 요청은 성공해야 한다.
        executorService.submit(() -> {
            try {
                latch.await();
                mockMvc.perform(requestBuilder)
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // 두 번째 요청도 성공해야 한다.
        executorService.submit(() -> {
            try {
                latch.await();
                mockMvc.perform(requestBuilder)
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(MockMvcResultMatchers.status().isOk());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        latch.countDown();  // 두 쓰레드가 동시에 실행되도록 함

        executorService.shutdown();
        while (!executorService.isTerminated()){}   // 모든 작업이 완료될 때까지 기다림
    }
}