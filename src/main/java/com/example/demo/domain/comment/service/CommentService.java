package com.example.demo.domain.comment.service;

import com.example.demo.domain.comment.dto.AddCommentRequest;
import com.example.demo.domain.comment.dto.UpdateCommentRequest;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.repository.CommentRepository;
import com.example.demo.domain.episode.entity.Episode;
import com.example.demo.domain.episode.repository.EpisodeRepository;
import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final EpisodeRepository episodeRepository;

    public Comment save(AddCommentRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                        .orElseThrow(() -> new IllegalArgumentException("not found memberId : " + request.getMemberId()));
        Episode episode = episodeRepository.findById(request.getEpisodeId())
                        .orElseThrow(() -> new IllegalArgumentException("not found episodeId : " + request.getEpisodeId()));

        return commentRepository.save(request.toEntity(member, episode));
    }

    public Comment findById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found commentId : " + id));
    }

    public void delete(long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found commentId : " + id));

        commentRepository.delete(comment);
    }

    @Transactional
    public Comment update(long id, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found commentId : " + id));

        comment.update(request);
        return comment;
    }

}
