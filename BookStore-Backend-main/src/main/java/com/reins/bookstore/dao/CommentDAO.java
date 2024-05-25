package com.reins.bookstore.dao;

import com.reins.bookstore.entity.Comment;
import org.springframework.data.domain.Page;

public interface CommentDAO {
    Page<Comment> getBookCommentsOrderByCreatedTime(Long bookId, Integer pageIndex, Integer pageSize);

    Page<Comment> getBookCommentsOrderByLike(Long bookId, Integer pageIndex, Integer pageSize);

    void addComment(Long bookId, Long userId, String content);

    void replyComment(Long userId, Long replyId, String content);

    boolean isLiked(Long userId, Long commentId);

    void likeComment(Long commentId, Long userId);

    void unlikeComment(Long commentId, Long userId);
}
