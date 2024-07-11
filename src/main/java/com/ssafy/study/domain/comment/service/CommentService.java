package com.ssafy.study.domain.comment.service;

import com.ssafy.study.domain.comment.dao.CommentRepository;
import com.ssafy.study.domain.comment.entity.Comment;
import com.ssafy.study.domain.comment.service.converter.CommentConverter;
import com.ssafy.study.domain.comment.ui.dto.CommentRequest;
import com.ssafy.study.domain.comment.ui.dto.CommentResponse;
import com.ssafy.study.domain.episode.dao.EpisodeRepository;
import com.ssafy.study.domain.episode.entity.Episode;
import com.ssafy.study.domain.member.dao.MemberRepository;
import com.ssafy.study.domain.member.entity.Member;
import com.ssafy.study.global.common.exception.BaseException;
import com.ssafy.study.global.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final EpisodeRepository episodeRepository;


    public void create(CommentRequest.CommentDto commentDto) {
        Episode episode = episodeRepository.findById(commentDto.episodeId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.EPISODE_NOT_FOUND));
        Member member = memberRepository.findById(commentDto.memberId())
                .orElseThrow(() -> new BaseException(BaseResponseStatus.MEMBER_NOT_FOUND));
        Comment comment = CommentConverter
                .toComment(commentDto.content(), episode, member);
        commentRepository.save(comment);
    }

    public CommentResponse.CommentInfoDto findByCommentId(Long commentId) {
        Comment comment = commentRepository.findByCommentWithMemberAndEpisodeAndWebtoon(commentId)
                .orElseThrow(() -> new BaseException(BaseResponseStatus.COMMENT_NOT_FOUND));
        return CommentConverter.toCommentInfoDto(comment);
    }

}