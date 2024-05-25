package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByBookOrderByCreatedAtDesc(Book book, Pageable pageable);

    Page<Comment> findAllByBookOrderByLikeDesc(Book book, Pageable pageable);
}
