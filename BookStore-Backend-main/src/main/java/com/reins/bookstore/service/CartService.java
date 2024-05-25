package com.reins.bookstore.service;

import com.reins.bookstore.entity.CartItem;
import com.reins.bookstore.models.ApiResponseBase;

import java.util.List;

public interface CartService {
    List<CartItem> getUserCartItems(Long userId);

    ApiResponseBase deleteItem(Long id, Long userId);

    ApiResponseBase updateItem(Long id, Integer number, Long userId);

    ApiResponseBase addItem(Long userId, Long bookId);
}
