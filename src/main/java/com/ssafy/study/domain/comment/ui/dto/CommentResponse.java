package com.ssafy.study.domain.comment.ui.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

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
