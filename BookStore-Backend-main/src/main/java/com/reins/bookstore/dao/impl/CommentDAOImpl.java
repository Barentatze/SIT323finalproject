package com.reins.bookstore.dao.impl;

import com.reins.bookstore.dao.CommentDAO;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Comment;
import com.reins.bookstore.entity.LikeRecord;
import com.reins.bookstore.entity.User;
import com.reins.bookstore.repository.CommentRepository;
import com.reins.bookstore.repository.LikeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Component
public class CommentDAOImpl implements CommentDAO {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    LikeRecordRepository likeRecordRepository;

    @Override
    public Page<Comment> getBookCommentsOrderByCreatedTime(Long bookId, Integer pageIndex, Integer pageSize) {
        return commentRepository.findAllByBookOrderByCreatedAtDesc(new Book(bookId), PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public Page<Comment> getBookCommentsOrderByLike(Long bookId, Integer pageIndex, Integer pageSize) {
        return commentRepository.findAllByBookOrderByLikeDesc(new Book(bookId), PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public void addComment(Long bookId, Long userId, String content) {
        Comment comment = new Comment(null, new Book(bookId), new User(userId), null, content, 0, new Timestamp(System.currentTimeMillis()));
        commentRepository.save(comment);
    }

    @Override
    public void replyComment(Long replyId, Long userId, String content) {
        Comment reply = commentRepository.findById(replyId).orElse(null);
        if (reply == null) {
            return;
        }
        Comment comment = new Comment(null, reply.getBook(), new User(userId), reply, content, 0, new Timestamp(System.currentTimeMillis()));
        commentRepository.save(comment);
    }

    @Override
    public boolean isLiked(Long userId, Long commentId) {
        LikeRecord record = likeRecordRepository.findByUserAndComment(new User(userId), new Comment(commentId));
        return record != null;
    }

    @Override
    @Transactional
    public void likeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        LikeRecord record = likeRecordRepository.findByUserAndComment(new User(userId), comment);
        if (record == null) {
            record = new LikeRecord(null, new User(userId), comment);
            likeRecordRepository.save(record);
            comment.setLike(comment.getLike() + 1);
            commentRepository.save(comment);
        }
    }

    @Override
    @Transactional
    public void unlikeComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        LikeRecord record = likeRecordRepository.findByUserAndComment(new User(userId), comment);
        if (record != null) {
            likeRecordRepository.delete(record);
            comment.setLike(comment.getLike() - 1);
            commentRepository.save(comment);
        }
    }
}
