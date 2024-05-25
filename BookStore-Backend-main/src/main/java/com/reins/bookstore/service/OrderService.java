package com.reins.bookstore.service;

import com.reins.bookstore.entity.Order;
import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.models.OrderInfo;

import java.util.List;

public interface OrderService {
    ApiResponseBase placeOrder(Long userId, OrderInfo orderInfo);

    List<Order> getOrders(Long userId);
}
