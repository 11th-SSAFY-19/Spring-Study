package com.ssafy.study.domain.comment.ui.dto;

import com.ssafy.study.domain.episode.entity.Episode;
import com.ssafy.study.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class CommentRequest {
    public record CommentDto(
            String content, Long memberId, Long episodeId) {
    }
}
