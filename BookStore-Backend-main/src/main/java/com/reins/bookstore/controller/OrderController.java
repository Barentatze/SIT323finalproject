package com.reins.bookstore.controller;

import com.reins.bookstore.entity.Order;
import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.models.OrderInfo;
import com.reins.bookstore.service.OrderService;
import com.reins.bookstore.utils.SessionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@Tag(name = "Order", description = "订单相关 API")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping
    @Operation(summary = "下单")
    ResponseEntity<ApiResponseBase> placeOrder(@RequestBody OrderInfo orderInfo) {
        return ResponseEntity.ok(orderService.placeOrder(SessionUtils.getUserId(), orderInfo));
    }

    @GetMapping
    @Operation(summary = "获取用户订单")
    ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders(SessionUtils.getUserId()));
    }
}
