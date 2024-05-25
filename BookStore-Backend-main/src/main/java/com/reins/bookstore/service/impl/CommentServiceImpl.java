package com.reins.bookstore.service.impl;

import com.reins.bookstore.constants.Messages;
import com.reins.bookstore.dao.CommentDAO;
import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDAO commentDAO;

    @Override
    public ApiResponseBase replyComment(Long id, Long userId, String content) {
        commentDAO.replyComment(id, userId, content);
        return ApiResponseBase.succeed(Messages.REPLY_COMMENT_SUCCEED);
    }

    @Override
    public ApiResponseBase likeComment(Long id, Long userId) {
        commentDAO.likeComment(id, userId);
        return ApiResponseBase.succeed(Messages.LIKE_COMMENT_SUCCEED);
    }

    @Override
    public ApiResponseBase unlikeComment(Long id, Long userId) {
        commentDAO.unlikeComment(id, userId);
        return ApiResponseBase.succeed(Messages.UNLIKE_COMMENT_SUCCEED);
    }
}
