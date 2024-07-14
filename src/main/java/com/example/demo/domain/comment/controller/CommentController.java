package com.example.demo.domain.comment.controller;


import com.example.demo.domain.comment.dto.AddCommentRequest;
import com.example.demo.domain.comment.dto.CommentResponse;
import com.example.demo.domain.comment.dto.UpdateCommentRequest;
import com.example.demo.domain.comment.entity.Comment;
import com.example.demo.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody AddCommentRequest request) {
        Comment savedComment = commentService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedComment);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findComment(@PathVariable long id) {
        Comment comment = commentService.findById(id);

        return ResponseEntity.ok()
                .body(new CommentResponse(comment));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable long id) {
        commentService.delete(id);

        return ResponseEntity.ok()
                .build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable long id, @RequestBody UpdateCommentRequest request) {
        Comment updatedComment = commentService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedComment);
    }
}
