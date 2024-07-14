package com.example.demo.domain.comment.service;

import com.example.demo.domain.comment.service.converter.CommentConverter;
import com.example.demo.domain.comment.ui.dto.CommentRequest;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.repository.CommentRepository;
import com.example.demo.domain.comment.ui.dto.CommentResponse;
import com.example.demo.domain.episode.entity.Episode;
import com.example.demo.domain.episode.repository.EpisodeRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.global.common.exception.BaseException;
import com.example.demo.global.common.response.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
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
