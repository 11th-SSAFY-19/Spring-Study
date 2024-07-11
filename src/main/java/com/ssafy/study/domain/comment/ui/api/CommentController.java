package com.ssafy.study.domain.comment.ui.api;

import com.ssafy.study.domain.comment.service.CommentService;
import com.ssafy.study.domain.comment.ui.dto.CommentRequest;
import com.ssafy.study.domain.comment.ui.dto.CommentResponse;
import com.ssafy.study.global.common.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
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