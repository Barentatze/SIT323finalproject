package com.reins.bookstore.service.impl;

import com.reins.bookstore.constants.Messages;
import com.reins.bookstore.dao.BookDAO;
import com.reins.bookstore.dao.CartDAO;
import com.reins.bookstore.dao.OrderDAO;
import com.reins.bookstore.dao.UserDAO;
import com.reins.bookstore.entity.Book;
import com.reins.bookstore.entity.CartItem;
import com.reins.bookstore.entity.Order;
import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.models.OrderInfo;
import com.reins.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;

    @Autowired
    UserDAO userDAO;

    @Autowired
    BookDAO bookDAO;

    @Autowired
    CartDAO cartDAO;

    private List<CartItem> getCartItems(List<Long> ids) {
        List<CartItem> cartItems = new ArrayList<>();
        for (Long cartItemId : ids) {
            CartItem cartItem = cartDAO.getById(cartItemId);
            if (cartItem == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            cartItems.add(cartItem);
        }
        return cartItems;
    }

    private Long computeTotalPrice(List<CartItem> cartItems) {
        long totalPrice = 0L;
        for (CartItem cartItem : cartItems) {
            Book book = cartItem.getBook();
            if (book == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            totalPrice += (long) book.getPrice() * cartItem.getNumber();
        }
        return totalPrice;
    }

    @Override
    @Transactional
    public ApiResponseBase placeOrder(Long userId, OrderInfo orderInfo) {
        List<CartItem> cartItems = getCartItems(orderInfo.getItemIds());

        long totalPrice = computeTotalPrice(cartItems);
        long userBalance = userDAO.getUserBalance(userId);

        if (totalPrice > userBalance) {
            return ApiResponseBase.fail(Messages.BALANCE_NOT_ENOUGH);
        }

        long rest = userBalance - totalPrice;
        userDAO.updateUserBalance(userId, rest);
        orderDAO.saveOrder(userId, orderInfo, cartItems);

        // update book sales
        bookDAO.updateSales(cartItems);

        // remove cart items
        cartDAO.removeAll(cartItems);
        return ApiResponseBase.succeed(Messages.PLACE_ORDER_SUCCEED);
    }

    @Override
    public List<Order> getOrders(Long userId) {
        return orderDAO.getByUserId(userId);
    }
}
