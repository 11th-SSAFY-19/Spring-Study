package com.example.demo.domain.comment.dto;

import com.example.demo.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class CommentResponse {
    private final String content;

    public CommentResponse(Comment comment) {
        this.content = comment.getContent();
    }
}
