package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Comment;
import com.reins.bookstore.entity.LikeRecord;
import com.reins.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRecordRepository extends JpaRepository<LikeRecord, Long> {
    LikeRecord findByUserAndComment(User user, Comment comment);
}
