package com.reins.bookstore.dao.impl;

import com.reins.bookstore.dao.OrderDAO;
import com.reins.bookstore.entity.CartItem;
import com.reins.bookstore.entity.Order;
import com.reins.bookstore.entity.OrderItem;
import com.reins.bookstore.entity.User;
import com.reins.bookstore.models.OrderInfo;
import com.reins.bookstore.repository.CartRepository;
import com.reins.bookstore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDAOImpl implements OrderDAO {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CartRepository cartRepository;

    @Override
    @Transactional
    public void saveOrder(Long userId, OrderInfo orderInfo, List<CartItem> items) {
        Order order = new Order(
                null,
                orderInfo.getReceiver(),
                orderInfo.getAddress(),
                orderInfo.getTel(),
                new Timestamp(System.currentTimeMillis()),
                new ArrayList<>(),
                new User(userId)
        );
        for (CartItem cartItem : items) {
            OrderItem item = new OrderItem(null, cartItem.getBook(), cartItem.getNumber(), order);
            order.getItems().add(item);
        }
        orderRepository.save(order);
    }

    @Override
    public List<Order> getByUserId(Long userId) {
        return orderRepository.findAllByUserOrderByCreatedAtDesc(new User(userId));
    }
}
