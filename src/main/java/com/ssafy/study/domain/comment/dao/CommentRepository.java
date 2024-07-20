package com.ssafy.study.domain.comment.dao;

import com.ssafy.study.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c " +
            "from Comment c " +
            "join fetch c.member m " +
            "join fetch c.episode e " +
            "join fetch e.webtoon w " +
            "where c.commentId = :id")
    Optional<Comment> findByCommentWithMemberAndEpisodeAndWebtoon(Long id);
}
