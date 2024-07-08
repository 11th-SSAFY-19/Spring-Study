package com.example.demo.domain.comment.dto;


import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.episode.entity.Episode;
import com.example.demo.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class AddCommentRequest {
    private String content;
    private Long memberId;
    private Long episodeId;

    public Comment toEntity(Member member, Episode episode) {
        return Comment.builder()
                .content(content)
                .member(member)
                .episode(episode)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
