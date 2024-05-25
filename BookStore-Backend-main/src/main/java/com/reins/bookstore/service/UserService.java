package com.reins.bookstore.service;

import com.reins.bookstore.entity.User;
import com.reins.bookstore.models.ApiResponseBase;

public interface UserService {
    User getUser(Long userId);

    ApiResponseBase changeMyPassword(Long userId, String password);
}
