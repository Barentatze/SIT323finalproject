package com.reins.bookstore.controller;

import com.reins.bookstore.entity.Book;
import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.models.CommentDTO;
import com.reins.bookstore.models.CommentRequest;
import com.reins.bookstore.models.PagedItems;
import com.reins.bookstore.service.BookService;
import com.reins.bookstore.utils.SessionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Book", description = "书籍相关 API")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/api/books")
    @Operation(summary = "搜索书籍")
    ResponseEntity<PagedItems<Book>> searchBooks(@RequestParam String keyword,
                                                 @RequestParam Integer pageIndex,
                                                 @RequestParam Integer pageSize) {
        if (pageIndex == null || pageIndex < 0 || pageSize == null || pageSize < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookService.searchBooks(keyword, pageIndex, pageSize));
    }

    @GetMapping("/api/book/{id}")
    @Operation(summary = "根据 ID 获取书籍")
    ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping("/api/books/rank")
    @Operation(summary = "获取书籍排名（top 10）")
    ResponseEntity<List<Book>> getTop10BestsellingBooks() {
        return ResponseEntity.ok(bookService.getTop10BestsellingBooks());
    }

    @GetMapping("/api/book/{id}/comments")
    @Operation(summary = "获取书籍评论")
    ResponseEntity<PagedItems<CommentDTO>> getBookComments(@PathVariable Long id,
                                                           @RequestParam Integer pageIndex,
                                                           @RequestParam Integer pageSize,
                                                           @RequestParam String sort) {
        if (pageIndex == null || pageIndex < 0 || pageSize == null || pageSize < 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookService.getBookComments(id, pageIndex, pageSize, sort, SessionUtils.getUserId()));
    }

    @PostMapping("/api/book/{id}/comments")
    @Operation(summary = "发布书籍评论")
    ResponseEntity<ApiResponseBase> addComment(@PathVariable Long id, @RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(bookService.addBookComment(id, SessionUtils.getUserId(), commentRequest.getContent()));
    }
}
