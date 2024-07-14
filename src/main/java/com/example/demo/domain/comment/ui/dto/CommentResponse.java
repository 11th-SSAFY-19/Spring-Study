package com.example.demo.domain.comment.ui.dto;

import com.example.demo.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class CommentResponse {
    @AllArgsConstructor
    @Builder
    public static class CommentInfoDto {
        private Long commentId;
        private String commentContent;

        private Long memberId;
        private String memberName;
        private Integer memberAge;

        private Long webtoonId;
        private String webtoonTitle;

        private Long episodeId;
        private String episodeTitle;
    }
}
