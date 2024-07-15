package com.example.demo.domain.comment.ui.api;


import com.example.demo.domain.comment.ui.dto.CommentRequest;
import com.example.demo.domain.comment.ui.dto.CommentResponse;
import com.example.demo.domain.comment.service.CommentService;
import com.example.demo.global.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public BaseResponse<Void> create(@RequestBody CommentRequest.CommentDto commentDto){
        commentService.create(commentDto);
        return BaseResponse.successCreating();
    }

    @GetMapping("/{commentId}")
    public BaseResponse<CommentResponse.CommentInfoDto> findByCommentId(@PathVariable(name = "commentId") Long commentId) {
        CommentResponse.CommentInfoDto commentDto = commentService.findByCommentId(commentId);
        return new BaseResponse<>(commentDto);
    }
}
