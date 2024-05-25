package com.reins.bookstore.controller;

import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.models.CommentRequest;
import com.reins.bookstore.service.CommentService;
import com.reins.bookstore.utils.SessionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Comment", description = "评论相关 API")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/api/comment/{id}")
    @Operation(summary = "回复评论")
    ResponseEntity<ApiResponseBase> replyComment(@PathVariable Long id, @RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(commentService.replyComment(id, SessionUtils.getUserId(), commentRequest.getContent()));
    }

    @PutMapping("/api/comment/{id}/like")
    @Operation(summary = "点赞评论")
    ResponseEntity<ApiResponseBase> likeComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.likeComment(id, SessionUtils.getUserId()));
    }

    @PutMapping("/api/comment/{id}/unlike")
    @Operation(summary = "取消点赞评论")
    ResponseEntity<ApiResponseBase> unlikeComment(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.unlikeComment(id, SessionUtils.getUserId()));
    }
}
