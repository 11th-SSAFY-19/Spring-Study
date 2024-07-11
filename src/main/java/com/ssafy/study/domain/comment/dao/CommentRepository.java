package com.ssafy.study.domain.comment.dao;

import com.ssafy.study.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c " +
            "from Comment c " +
            "join fetch Member m " +
            "join fetch Episode e " +
            "join fetch Webtoon w " +
            "where c.commentId = :id")
    Optional<Comment> findByCommentWithMemberAndEpisodeAndWebtoon(Long id);
}
