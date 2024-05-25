package com.reins.bookstore.dao.impl;

import com.reins.bookstore.dao.BookDAO;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.CartItem;
import com.reins.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class BookDAOImpl implements BookDAO {
    @Autowired
    BookRepository bookRepository;

    @Override
    public Page<Book> searchBooksByKeyword(String keyword, Integer pageIndex, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        String wrapped = "%" + keyword + "%";
        return bookRepository.findAllByAuthorLikeOrTitleLike(wrapped, wrapped, pageable);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateSales(List<CartItem> cartItems) {
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            if (book == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            book.setSales(book.getSales() + cartItem.getNumber());
            bookRepository.save(book);
        }
    }

    @Override
    public List<Book> getTop10BestsellingBooks() {
        return bookRepository.findTop10ByOrderBySalesDesc();
    }
}
