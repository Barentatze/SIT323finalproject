package com.reins.bookstore.dao;

import com.reins.bookstore.entity.CartItem;
import com.reins.bookstore.entity.Order;
import com.reins.bookstore.models.OrderInfo;

import java.util.List;

public interface OrderDAO {
    void saveOrder(Long userId, OrderInfo orderInfo, List<CartItem> items);

    List<Order> getByUserId(Long userId);
}
