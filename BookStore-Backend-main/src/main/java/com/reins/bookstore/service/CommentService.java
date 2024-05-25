package com.reins.bookstore.service;

import com.reins.bookstore.models.ApiResponseBase;

public interface CommentService {
    ApiResponseBase replyComment(Long id, Long userId, String content);

    ApiResponseBase likeComment(Long id, Long userId);

    ApiResponseBase unlikeComment(Long id, Long userId);
}
