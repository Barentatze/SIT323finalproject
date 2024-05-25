package com.reins.bookstore.service;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.models.CommentDTO;
import com.reins.bookstore.models.PagedItems;

import java.util.List;


public interface BookService {
    PagedItems<Book> searchBooks(String keyword, Integer pageIndex, Integer pageSize);

    Book getBookById(Long id);

    List<Book> getTop10BestsellingBooks();

    PagedItems<CommentDTO> getBookComments(Long bookId, Integer pageIndex, Integer pageSize, String sort, Long userId);

    ApiResponseBase addBookComment(Long id, Long userId, String content);
}
