package com.ssafy.study.domain.comment.ui.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;

public class CommentResponse {
    @AllArgsConstructor
    @Builder
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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
