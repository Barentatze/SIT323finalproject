package com.reins.bookstore.service.impl;

import com.reins.bookstore.constants.Messages;
import com.reins.bookstore.dao.CartDAO;
import com.reins.bookstore.entity.CartItem;
import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDAO cartDAO;

    @Override
    public List<CartItem> getUserCartItems(Long userId) {
        return cartDAO.getUserItems(userId);
    }

    @Override
    @Transactional
    public ApiResponseBase deleteItem(Long id, Long userId) {
        CartItem item = cartDAO.getById(id);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(item.getUser().getId(), userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        cartDAO.deleteById(id);
        return ApiResponseBase.succeed(Messages.DELETE_SUCCEED);
    }

    @Override
    @Transactional
    public ApiResponseBase updateItem(Long id, Integer number, Long userId) {
        CartItem item = cartDAO.getById(id);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!Objects.equals(item.getUser().getId(), userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        cartDAO.updateItem(item, number);
        return ApiResponseBase.succeed(Messages.MODIFY_SUCCEED);
    }

    @Override
    @Transactional
    public ApiResponseBase addItem(Long userId, Long bookId) {
        if (cartDAO.exists(userId, bookId)) {
            return ApiResponseBase.fail(Messages.CART_ITEM_EXISTS);
        }

        cartDAO.addOne(userId, bookId);
        return ApiResponseBase.succeed(Messages.ADD_CART_SUCCEED);
    }
}
