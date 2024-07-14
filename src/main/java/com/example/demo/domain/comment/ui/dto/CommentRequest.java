package com.example.demo.domain.comment.ui.dto;


import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.episode.entity.Episode;
import com.example.demo.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class CommentRequest {

    public record CommentDto(
            String content, Long memberId, Long episodeId) {
    }
}
