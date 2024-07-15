package com.example.demo.domain.episode.ui.dto;

import com.example.demo.domain.episode.entity.Episode;

import java.time.LocalDateTime;

public class GetEpisodeResponseDto {

    private Long episodeId;
    private String title;
    private Integer viewCount;
//    private List<Comment> commentList= new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public GetEpisodeResponseDto(Long episodeId, String title, Integer viewCount, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.episodeId = episodeId;
        this.title = title;
        this.viewCount = viewCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static GetEpisodeResponseDto from (Episode episode) {
        return new GetEpisodeResponseDto(
                episode.getEpisodeId(),
                episode.getTitle(),
                episode.getViewCount(),
                episode.getCreatedAt(),
                episode.getUpdatedAt()
        );
    }

    // Getters and Setters
    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
