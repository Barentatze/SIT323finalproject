package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByAuthorLikeOrTitleLike(String author, String title, Pageable pageable);

    List<Book> findTop10ByOrderBySalesDesc();
}
