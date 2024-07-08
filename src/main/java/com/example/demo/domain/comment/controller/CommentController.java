package com.example.demo.domain.comment.controller;

import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Comment comment){
        commentService.create(comment);
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> findByCommentId(@PathVariable(name = "commentId") Long commentId) {
        Comment comment = commentService.findByCommentId(commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
