package com.ssafy.study.domain.comment.service.converter;

import com.ssafy.study.domain.comment.entity.Comment;
import com.ssafy.study.domain.comment.ui.dto.CommentResponse;
import com.ssafy.study.domain.episode.entity.Episode;
import com.ssafy.study.domain.member.entity.Member;

public class CommentConverter {
    public static Comment toComment(String content, Episode episode, Member member) {
        return Comment.builder()
                .content(content)
                .episode(episode)
                .member(member)
                .build();
    }

    public static CommentResponse.CommentInfoDto toCommentInfoDto(Comment comment) {
        return CommentResponse.CommentInfoDto.builder()
                .commentId(comment.getCommentId())
                .commentContent(comment.getContent())
                .episodeId(comment.getEpisode().getEpisodeId())
                .episodeTitle(comment.getEpisode().getTitle())
                .memberId(comment.getMember().getMemberId())
                .memberAge(comment.getMember().getAge())
                .memberName(comment.getMember().getName())
                .webtoonId(comment.getEpisode().getWebtoon().getWebtoonId())
                .webtoonTitle(comment.getEpisode().getWebtoon().getTitle())
                .build();
    }

}
