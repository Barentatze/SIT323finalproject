package com.reins.bookstore.dao.impl;

import com.reins.bookstore.dao.CartDAO;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.CartItem;
import com.reins.bookstore.entity.User;
import com.reins.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartDAOImpl implements CartDAO {
    @Autowired
    CartRepository cartRepository;

    @Override
    public List<CartItem> getUserItems(Long userId) {
        return cartRepository.findAllByUserOrderByIdDesc(new User(userId));
    }

    @Override
    public void deleteById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public CartItem getById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    @Override
    public void addOne(Long userId, Long bookId) {
        CartItem item = new CartItem(null, new User(userId), new Book(bookId), 1);
        cartRepository.save(item);
    }

    @Override
    public boolean exists(Long userId, Long bookId) {
        return cartRepository.existsByUserAndBook(new User(userId), new Book(bookId));
    }

    @Override
    public void updateItem(CartItem item, Integer number) {
        item.setNumber(number);
        cartRepository.save(item);
    }

    @Override
    public void removeAll(List<CartItem> cartItems) {
        cartRepository.deleteAll(cartItems);
    }
}
