package com.reins.bookstore.repository;

import com.reins.bookstore.entity.Order;
import com.reins.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserOrderByCreatedAtDesc(User user);
}
